package ru.github.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class IssuesPage {
	public SelenideElement issue (int id){
		return  $(String.format("#issue_%d_link",id));
	}
}
