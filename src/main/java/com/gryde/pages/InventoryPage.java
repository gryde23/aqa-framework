package com.gryde.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.gryde.pages.components.HeaderComponent;
import com.gryde.pages.components.ItemCardComponent;

import java.util.List;

import static com.codeborne.selenide.Selectors.byTestId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class InventoryPage extends BasePage<InventoryPage>{

    private final HeaderComponent header = new HeaderComponent($(byTestId("primary-header")));
    private final ElementsCollection itemCards = $$(".inventory_item");
    private final SelenideElement sortSelect = $(byTestId("product-sort-container"));

    @Override
    protected String path() {
        return "/inventory.html";
    }

    @Override
    protected SelenideElement uniqueElement() {
        return null;
    }

    public InventoryPage addItemsToCart(int amountOfItems) {
        itemCards.shouldHave(CollectionCondition.sizeGreaterThanOrEqual(amountOfItems));

        itemCards.asFixedIterable().stream()
                .limit(amountOfItems)
                .map(ItemCardComponent::new)
                .forEach(ItemCardComponent::addToCart);

        return this;
    }

    public String getAmountOfItemsInCart() {
        return header.getCartBadgeNum();
    }

    public InventoryPage removeItemFromCart(String itemTitle) {
        itemCards.asFixedIterable().stream()
                .map(ItemCardComponent::new)
                .filter(i -> i.getTitle().equals(itemTitle))
                .findFirst()
                .ifPresent(ItemCardComponent::removeFromCart);

        return this;
    }

    public InventoryPage addItemToCart(String itemTitle) {
        itemCards.asFixedIterable().stream()
                .map(ItemCardComponent::new)
                .filter(i -> i.getTitle().equals(itemTitle))
                .findFirst()
                .ifPresent(ItemCardComponent::addToCart);

        return this;
    }

    public InventoryPage selectItemsSort(String value) {
        sortSelect.selectOptionByValue(value);
        return this;
    }

    public List<String> getAllPrices() {
        return itemCards.asFixedIterable().stream()
                .map(item -> new ItemCardComponent(item).getPrice())
                .toList();
    }

    public CartPage goToCart() {
        header.clickCartLink();
        return new CartPage();
    }
}
