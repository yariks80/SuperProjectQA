package com.it.driver;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;

import java.util.List;

public class WrappedWebElement  implements WebElement, WrapsElement, Locatable {
    private WebElement wrapped;

    public WrappedWebElement(WebElement wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void click() {
        wrapped.click();
    }

    @Override
    public void submit() {
      wrapped.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
       wrapped.sendKeys(keysToSend);
    }

    @Override
    public void clear() {
     wrapped.clear();
    }

    @Override
    public String getTagName() {
        return wrapped.getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return wrapped.getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return wrapped.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return wrapped.isEnabled();
    }

    @Override
    public String getText() {
        return wrapped.getText().trim();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return wrapped.findElements(by);
    }

    @Override
    public WrappedWebElement findElement(By by) {
        return  new WrappedWebElement(wrapped.findElement(by));
    }

    @Override
    public boolean isDisplayed() {
        return wrapped.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return wrapped.getLocation();
    }

    @Override
    public Dimension getSize() {
        return wrapped.getSize();
    }

    @Override
    public Rectangle getRect() {
        return wrapped.getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return wrapped.getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return wrapped.getScreenshotAs(target);
    }

    @Override
    public WebElement getWrappedElement() {
        return wrapped;
    }

    public void sendKeysOneByOne(String text) {
        for (String s : text.split("")) {
            wrapped.sendKeys(s);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Coordinates getCoordinates() {
        return ((Locatable)wrapped).getCoordinates();
    }
}
