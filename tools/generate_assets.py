"""Generate the simple item models, language files and pack icon.

The entity renderers deliberately reuse vanilla models/textures; tier is shown
by the 1.5x / 2.5x / 4.0x scale attribute and by the spawn egg colours.
"""
from __future__ import annotations

import json
import zipfile
from pathlib import Path

from PIL import Image, ImageDraw

ROOT = Path(__file__).resolve().parents[1]
RES = ROOT / "src" / "main" / "resources"
ASSETS = RES / "assets" / "compressed_animals"
MODEL_DIR = ASSETS / "models" / "item"
LANG_DIR = ASSETS / "lang"

ANIMALS = [
    ("cow", "牛", "Cow"),
    ("sheep", "羊", "Sheep"),
    ("pig", "猪", "Pig"),
    ("chicken", "鸡", "Chicken"),
]


def write_json(path: Path, value: object) -> None:
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(value, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")


def generate_models() -> None:
    for animal, _, _ in ANIMALS:
        for tier in (1, 2, 3):
            name = f"compressed_{animal}_spawn_egg_{tier}"
            write_json(
                MODEL_DIR / f"{name}.json",
                {"parent": "minecraft:item/template_spawn_egg"},
            )


def generate_lang() -> None:
    en = {"itemGroup.compressed_animals": "Compressed Animals"}
    zh = {"itemGroup.compressed_animals": "压缩动物"}

    for animal, zh_name, en_name in ANIMALS:
        for tier in (1, 2, 3):
            tier_zh = {1: "一级", 2: "二级", 3: "三级"}[tier]
            tier_en = {1: "I", 2: "II", 3: "III"}[tier]
            entity_id = f"compressed_{animal}_{tier}"
            egg_id = f"compressed_{animal}_spawn_egg_{tier}"
            en[f"entity.compressed_animals.{entity_id}"] = f"Compressed {en_name} {tier_en}"
            zh[f"entity.compressed_animals.{entity_id}"] = f"{tier_zh}压缩{zh_name}"
            en[f"item.compressed_animals.{egg_id}"] = f"Compressed {en_name} Spawn Egg {tier_en}"
            zh[f"item.compressed_animals.{egg_id}"] = f"{tier_zh}压缩{zh_name}刷怪蛋"

    write_json(LANG_DIR / "en_us.json", en)
    write_json(LANG_DIR / "zh_cn.json", zh)


def generate_icon() -> None:
    client_jar = (
        ROOT.parent
        / ".gradle-home"
        / "caches"
        / "fabric-loom"
        / "1.21.1"
        / "minecraft-client.jar"
    )
    icon = Image.new("RGBA", (128, 128), (24, 24, 20, 255))
    draw = ImageDraw.Draw(icon)

    # Subtle checkerboard so the icon reads as a compressed block.
    for y in range(0, 128, 16):
        for x in range(0, 128, 16):
            if (x // 16 + y // 16) % 2 == 0:
                draw.rectangle((x, y, x + 15, y + 15), fill=(32, 30, 24, 255))

    with zipfile.ZipFile(client_jar) as jar:
        raw = jar.read("assets/minecraft/textures/entity/cow/cow.png")
    import io

    cow = Image.open(io.BytesIO(raw)).convert("RGBA")
    # The front of the cow's head is the readable 16x12 patch at the top-left
    # of the vanilla cow texture (the rest is legs/body UV islands).
    cow = cow.crop((0, 4, 16, 16)).resize((112, 84), Image.Resampling.NEAREST)
    icon.alpha_composite(cow, (8, 22))

    # Gold frame and tier badge.
    draw.rectangle((0, 0, 127, 127), outline=(255, 215, 0, 255), width=5)
    draw.rectangle((5, 5, 122, 122), outline=(120, 84, 20, 255), width=2)
    badge = [(108, 78), (126, 96), (108, 114), (90, 96)]
    draw.polygon(badge, fill=(255, 215, 0, 255), outline=(90, 60, 10, 255))
    draw.text((102, 88), "2", fill=(70, 42, 8, 255))

    ASSETS.mkdir(parents=True, exist_ok=True)
    icon.save(ASSETS / "icon.png")


def main() -> None:
    generate_models()
    generate_lang()
    generate_icon()
    print(f"generated {len(ANIMALS) * 3} models, 2 lang files and icon")


if __name__ == "__main__":
    main()
