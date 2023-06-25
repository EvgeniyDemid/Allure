package ru.github.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.By.linkText;

public class SearchResultPage {

	public SelenideElement repositoryResult(String resultLink){
		return  $(linkText(resultLink));
	}
}
