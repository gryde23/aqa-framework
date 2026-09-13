package com.gryde.pages.components;

import com.codeborne.selenide.SelenideElement;

public abstract class BaseComponent {

    private final SelenideElement root;

    public BaseComponent(SelenideElement root) {
        this.root = root;
    }

    protected SelenideElement root() {
        return root;
    }
}
