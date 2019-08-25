package com.nuclearfarts.morepotions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.nuclearfarts.morepotions.mixin.BrewingRecipeRegistryAccessor;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class MorePotions {

	private MorePotions() {}
	
	private static final Collection<RecipeToInit> RECIPES = new ArrayList<RecipeToInit>();
	
	public static final Potion HASTE = register("haste", new Potion(new StatusEffectInstance(StatusEffects.HASTE, 3600)), Items.GOLD_NUGGET, Potions.SWIFTNESS);
	public static final Potion LONG_HASTE = register("long_haste", new Potion(new StatusEffectInstance(StatusEffects.HASTE, 9600)));
	public static final Potion STRONG_HASTE = register("strong_haste", new Potion(new StatusEffectInstance(StatusEffects.HASTE, 1800, 1)));
	
	public static final Potion MINING_FATIGUE = register("mining_fatigue", new Potion(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 3600)), Items.FERMENTED_SPIDER_EYE, HASTE);
	public static final Potion LONG_MINING_FATIGUE = register("long_mining_fatigue", new Potion(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 9600)));
	public static final Potion STRONG_MINING_FATIGUE = register("strong_mining_fatigue", new Potion(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 1800, 1)));
	
	public static final Potion UNLUCK = register("unluck", new Potion(new StatusEffectInstance(StatusEffects.UNLUCK, 6000)), Items.FERMENTED_SPIDER_EYE, Potions.LUCK);
	
	public static final Potion NAUSEA = register("nausea", new Potion(new StatusEffectInstance(StatusEffects.NAUSEA, 900)), Items.POISONOUS_POTATO, Potions.AWKWARD);
	public static final Potion LONG_NAUSEA = register("long_nausea", new Potion(new StatusEffectInstance(StatusEffects.NAUSEA, 1800)));
	
	public static final Potion BLINDNESS = register("blindness", new Potion(new StatusEffectInstance(StatusEffects.BLINDNESS, 400)), Items.INK_SAC, Potions.AWKWARD);
	public static final Potion LONG_BLINDNESS = register("long_blindness", new Potion(new StatusEffectInstance(StatusEffects.BLINDNESS, 800)));
	
	public static final Potion LEVITATION = register("levitation", new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 200)), Items.FERMENTED_SPIDER_EYE, Potions.SLOW_FALLING);
	public static final Potion LONG_LEVITATION = register("long_levitation", new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 300)));
	public static final Potion STRONG_LEVITATION = register("strong_levitation", new Potion(new StatusEffectInstance(StatusEffects.LEVITATION, 30, 9)));
	
	public static final Potion RESISTANCE = register("resistance", new Potion(new StatusEffectInstance(StatusEffects.RESISTANCE, 3600)), Items.NAUTILUS_SHELL, Potions.AWKWARD);
	public static final Potion LONG_RESISTANCE = register("long_resistance", new Potion(new StatusEffectInstance(StatusEffects.RESISTANCE, 9600)));
	public static final Potion STRONG_RESISTANCE = register("strong_resistance", new Potion(new StatusEffectInstance(StatusEffects.RESISTANCE, 1800, 1)));
	
	public static final Potion SATURATION = register("saturation", new Potion(new StatusEffectInstance(StatusEffects.SATURATION, 1, 7)), Items.BEETROOT, Potions.AWKWARD);
	public static final Potion STRONG_SATURATION = register("strong_saturation", new Potion(new StatusEffectInstance(StatusEffects.SATURATION, 1, 15)));
	
	public static final Potion HUNGER = register("hunger", new Potion(new StatusEffectInstance(StatusEffects.HUNGER, 90 * 20)), Items.FERMENTED_SPIDER_EYE, SATURATION);
	public static final Potion LONG_HUNGER = register("long_hunger", new Potion(new StatusEffectInstance(StatusEffects.HUNGER, 3 * 60 * 20)));
	public static final Potion STRONG_HUNGER = register("strong_hunger", new Potion(new StatusEffectInstance(StatusEffects.HUNGER, 60 * 20, 1)));
	
	public static final Potion WITHER = register("wither", new Potion(new StatusEffectInstance(StatusEffects.WITHER, 45 * 20)), Items.WITHER_ROSE, Potions.AWKWARD);
	public static final Potion LONG_WITHER = register("long_wither", new Potion(new StatusEffectInstance(StatusEffects.WITHER, 90 * 20)));
	public static final Potion STRONG_WITHER = register("strong_wither", new Potion(new StatusEffectInstance(StatusEffects.WITHER, 450, 1)));
	
	public static final Potion ABSORPTION = register("absorption", new Potion(new StatusEffectInstance(StatusEffects.ABSORPTION, 2 * 60 * 20, 1)), Items.GOLDEN_APPLE, Potions.AWKWARD);
	public static final Potion LONG_ABSORPTION = register("long_absorption", new Potion(new StatusEffectInstance(StatusEffects.ABSORPTION, 4 * 60 * 20, 1)));
	public static final Potion STRONG_ABSORPTION = register("strong_absorption", new Potion(new StatusEffectInstance(StatusEffects.ABSORPTION, 1 * 60 * 20, 3)));
	
	public static final Potion HEALTH_BOOST = register("health_boost", new Potion(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 2 * 60 * 20)), Items.GLISTERING_MELON_SLICE, ABSORPTION);
	public static final Potion LONG_HEALTH_BOOST = register("long_health_boost", new Potion(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 4 * 60 * 20)));
	public static final Potion STRONG_HEALTH_BOOST = register("strong_health_boost", new Potion(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 60 * 20, 1)));
	
	public static void init() {
		RECIPES.forEach(RecipeToInit::init);
		mapPotions(ABSORPTION, Items.FERMENTED_SPIDER_EYE, Potions.HARMING);
		mapPotions(HEALTH_BOOST, Items.FERMENTED_SPIDER_EYE, Potions.HARMING);
		mapPotions(Potions.AWKWARD, Items.EMERALD, Potions.LUCK); //not brewable in vanilla
	}
	
	private static Potion register(String id, Potion potion) {
		return Registry.register(Registry.POTION, new Identifier(Init.MODID, id), potion);
	}
	
	private static Potion register(String id, Potion potion, Item ingredient, Potion from) {
		RECIPES.add(new RecipeToInit(from, ingredient, potion));
		return Registry.register(Registry.POTION, new Identifier(Init.MODID, id), potion);
	}
	
	private static void mapPotions(Potion in, Item ingredient, Potion result) {
		Identifier potionInId = Registry.POTION.getId(in);
		Identifier potionOutId = Registry.POTION.getId(result);
		Optional<Potion> inLong = Registry.POTION.getOrEmpty(new Identifier(potionInId.getNamespace(), "long_" + potionInId.getPath()));
		Optional<Potion> inStrong = Registry.POTION.getOrEmpty(new Identifier(potionInId.getNamespace(), "strong_" + potionInId.getPath()));
		Optional<Potion> outLong = Registry.POTION.getOrEmpty(new Identifier(potionOutId.getNamespace(), "long_" + potionOutId.getPath()));
		Optional<Potion> outStrong = Registry.POTION.getOrEmpty(new Identifier(potionOutId.getNamespace(), "strong_" + potionOutId.getPath()));
		if(outLong.isPresent() && inLong.isPresent()) {
			BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(inLong.get(), ingredient, outLong.get());
		}
		if(outStrong.isPresent() && inStrong.isPresent()) {
			BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(inStrong.get(), ingredient, outStrong.get());
		}
		BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(in, ingredient, result);
	}
	
	private static void variantRecipes(Potion potion) {
		Identifier id = Registry.POTION.getId(potion);
		Optional<Potion> lengthy = Registry.POTION.getOrEmpty(new Identifier(id.getNamespace(), "long_" + id.getPath()));
		Optional<Potion> strong = Registry.POTION.getOrEmpty(new Identifier(id.getNamespace(), "strong_" + id.getPath()));
		if(lengthy.isPresent()) {
			BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(potion, Items.REDSTONE, lengthy.get());
		}
		if(strong.isPresent()) {
			BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(potion, Items.GLOWSTONE_DUST, strong.get());
		}
	}
	
	private static class RecipeToInit {
		
		private final Potion in;
		private final Item ingredient;
		private final Potion result;
		
		private RecipeToInit(Potion in, Item ingredient, Potion result) {
			this.in = in;
			this.ingredient = ingredient;
			this.result = result;
		}
		
		public void init() {
			mapPotions(in, ingredient, result);
			variantRecipes(result);
		}
	}
}
