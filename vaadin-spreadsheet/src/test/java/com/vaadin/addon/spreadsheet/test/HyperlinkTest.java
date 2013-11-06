package com.vaadin.addon.spreadsheet.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.remote.DesiredCapabilities;

public class HyperlinkTest extends Test1 {
    @Test
    public void testBasicHyperlinks() {
        // FIXME on IE
        // "org.openqa.selenium.UnhandledAlertException: Modal dialog present:"
        // happens when opening the popup
        if (getDesiredCapabilities().getBrowserName().equalsIgnoreCase(
                DesiredCapabilities.internetExplorer().getBrowserName())) {
            return;
        }
        loadServerFixture("HYPERLINKS");

        c.clickCell("B3");
        try {
            popup.switchToPopup();
        } catch (UnhandledAlertException uae) {
        }
        page.assertTextPresent(new String[] { "HTTP Status 404 - /file-path",
                "HTTP ERROR 404" });
        popup.backToMainWindow();

        c.selectCell("B2");
        c.clickCell("C3");
        Assert.assertEquals(c.getCellContent("C3"), "new value");

    }

    @Test
    public void testFromUpload() {

        loadSheetFile("spreadsheet_hyperlinks.xlsx");

        c.clickCell("A4");
        Assert.assertEquals(c.getSelectedCell(), "B4");

        // FIXME on IE
        // "org.openqa.selenium.UnhandledAlertException: Modal dialog present:"
        // happens when opening the popup
        if (getDesiredCapabilities().getBrowserName().equalsIgnoreCase(
                DesiredCapabilities.internetExplorer().getBrowserName())) {
            return;
        }
        c.clickCell("A3");
        try {
            popup.switchToPopup();
        } catch (UnhandledAlertException uae) {
        }
        page.assertUrlContains("google");
        popup.backToMainWindow();

        // c.clickCell("A2");
        // switchToPopup();
        // assertCurrentUrlContains("google");
        // backToMainWindow();

        // c.clickCell("A4");
        // Assert.assertEquals("B4", c.getSelectedCell());
    }
}
