package ru.github.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
	public SelenideElement searchGitHubInput = $("input[placeholder='Search GitHub']");
}
