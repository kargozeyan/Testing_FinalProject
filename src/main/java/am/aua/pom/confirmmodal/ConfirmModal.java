package am.aua.pom.confirmmodal;

import am.aua.pom.BasePOM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import static am.aua.pom.confirmmodal.ConfirmModalLocators.CONFIRM_BUTTON;

public class ConfirmModal extends BasePOM {
    public ConfirmModal(WebDriver driver, Wait<WebDriver> wait) {
        super(driver, wait);
    }

    public void confirm() {
        wait.until(ExpectedConditions.elementToBeClickable(CONFIRM_BUTTON)).click();
    }
}
