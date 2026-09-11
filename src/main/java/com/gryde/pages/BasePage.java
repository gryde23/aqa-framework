package com.gryde.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public abstract class BasePage<T extends BasePage<T>> {

    protected abstract String path();
    protected abstract SelenideElement uniqueElement();

    @SuppressWarnings("unchecked")
    public T open() {
        Selenide.open(path());
        uniqueElement().shouldBe(Condition.visible);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T verifyIsLoaded() {
        uniqueElement().shouldBe(Condition.visible);
        return (T) this;
    }
}
