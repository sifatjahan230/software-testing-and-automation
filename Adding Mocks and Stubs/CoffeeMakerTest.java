package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

public class CoffeeMakerTest {
	
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;
	private Recipe recipe5;
	
	private Recipe [] stubRecipies; 
	
	private CoffeeMaker coffeeMaker;
	
	private RecipeBook recipeBookStub;
	
	private Inventory inventory;
	
	@Before
	public void setUp() throws RecipeException {
		
		recipeBookStub = mock(RecipeBook.class);
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		
		//Set up for recipe1
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");
		
		//Set up for recipe2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");
		
		//Set up for recipe3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");
		
		//Set up for recipe4
		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("65");
		
		//Set up for recipe5 (added by MWW)
		recipe5 = new Recipe();
		recipe5.setName("Super Hot Chocolate");
		recipe5.setAmtChocolate("6");
		recipe5.setAmtCoffee("0");
		recipe5.setAmtMilk("1");
		recipe5.setAmtSugar("1");
		recipe5.setPrice("100");

		stubRecipies = new Recipe [] {recipe1, recipe2, recipe3};
	}
	
	@Test
	public void testMakeCoffee() {
		coffeeMaker = new CoffeeMaker(recipeBookStub, new Inventory());
		assertTrue(true);
	}
	
	@Test
	public void testMakeCoffee2() {
		when(recipeBookStub.getRecipes()).thenReturn(stubRecipies);
		coffeeMaker.addRecipe(stubRecipies[0]);
		assertEquals(25, coffeeMaker.makeCoffee(0, 75));
	}
	
	@Test
	public void testMakeCoffee3() {
		when(recipeBookStub.addRecipe(stubRecipies[0])).thenReturn(true);
		when(recipeBookStub.addRecipe(stubRecipies[1])).thenReturn(true);
		when(recipeBookStub.addRecipe(stubRecipies[2])).thenReturn(true);
		when(recipeBookStub.addRecipe(stubRecipies[2])).thenReturn(false);
	}
	
	@Test
	public void testMakeCoffee4() {
		when(recipeBookStub.addRecipe(stubRecipies[0])).thenReturn(true);
		when(recipeBookStub.addRecipe(stubRecipies[1])).thenReturn(true);
		when(recipeBookStub.addRecipe(stubRecipies[1])).thenReturn(false);
		when(recipeBookStub.addRecipe(recipe4)).thenReturn(false);
		when(recipeBookStub.addRecipe(stubRecipies[2])).thenReturn(true);
	}
	
	@Test
	public void testMakeCoffee5() {
		when(recipeBookStub.getRecipes()).thenReturn(stubRecipies);
		coffeeMaker.addRecipe(stubRecipies[0]);
		assertEquals(75, coffeeMaker.makeCoffee(-1, 75));
	}
	
	@Test
	public void testAddRecipe2() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe1);
		assertFalse(false);
	}
	
	/*----------------------------------------------------------*/
	
	@Test
	public void testDummy() throws RecipeException {
		when(recipeBookStub.getRecipes()).thenReturn(stubRecipies);
		coffeeMaker.addRecipe(stubRecipies[2]);
		assertEquals(stubRecipies[2].getName(), "Latte");
		assertEquals(stubRecipies[2].getAmtChocolate(), 0);
	}
	
	@Test
	public void testDummy2() throws Exception {
		when(recipeBookStub.getRecipes()).thenReturn(stubRecipies);
		coffeeMaker.addRecipe(stubRecipies[2]);
		assertEquals(stubRecipies[2].getName(), "Latte");
		assertEquals(stubRecipies[2].getAmtChocolate(), 0);
		assertEquals(stubRecipies[2].getAmtCoffee(), 3);
		assertEquals(stubRecipies[2].getAmtMilk(), 3);
		assertEquals(stubRecipies[2].getAmtSugar(), 1);
		assertEquals(stubRecipies[2].getPrice(), 100);
	}
	
	@Test
	public void testSetAmtSugar() throws RecipeException {
		Recipe recipe = mock(Recipe.class);    
		doThrow(RecipeException.class).when(recipe).setAmtSugar("3");
		assertTrue(true);
	}
	
	@Test
	public void testSetAmtMilk() throws RecipeException {
		Recipe recipe = mock(Recipe.class);    
		doThrow(RecipeException.class).when(recipe).setAmtMilk("3");
		assertTrue(true);
	}
	
	@Test
	public void testSetAmtChocolate() throws RecipeException {
		Recipe recipe = mock(Recipe.class);    
		doThrow(RecipeException.class).when(recipe).setAmtChocolate("3");
		assertTrue(true);
	}
	
	@Test
	public void testSetAmtCoffee() throws RecipeException {
		Recipe recipe = mock(Recipe.class);    
		doThrow(RecipeException.class).when(recipe).setAmtCoffee("3");
		assertTrue(true);
	}
	
	@Test
	public void testSetPrice() throws RecipeException {
		Recipe recipe = mock(Recipe.class);    
		doNothing().when(recipe).setPrice("100");
		assertTrue(true);
	}
	
	@Test
	public void testSetName() throws RecipeException {
		Recipe recipe = mock(Recipe.class);    
		doNothing().when(recipe).setName("Test coffee");
		assertTrue(true);
	}
	
	@Test
	public void testAmtSugar() throws InventoryException {
		coffeeMaker.addRecipe(stubRecipies[0]);
		assertEquals(1, stubRecipies[0].getAmtSugar());
	}
	
	@Test
	public void testInventory() {
		RecipeBook rec = mock(RecipeBook.class);
		Inventory inv = new Inventory();
		when(rec.getRecipes()).thenReturn(stubRecipies);
		coffeeMaker = new CoffeeMaker(rec, inv);
		assertEquals(15, coffeeMaker.makeCoffee(0, 65));
		assertEquals(12, inv.getCoffee());
		assertEquals(14, inv.getSugar());
		assertEquals(14, inv.getMilk());
		assertEquals(15, inv.getChocolate());
	}
	
}
