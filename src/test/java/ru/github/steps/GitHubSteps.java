package ru.github.steps;

import io.qameta.allure.Step;
import ru.github.pages.IssuesPage;
import ru.github.pages.MainApplicationPage;
import ru.github.pages.MainPage;
import ru.github.pages.SearchResultPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

public class GitHubSteps {
	MainPage mainPage = new MainPage();
	SearchResultPage searchResultPage = new SearchResultPage();
	MainApplicationPage mainApplicationPage = new MainApplicationPage();
	IssuesPage issuesPage = new IssuesPage();

	@Step("Открываем главную страницу")
	public void openMainPage(){
		open("");
	}
	@Step("Ввести в поле поиска {allureRepo}  и нажать enter")
	public void enterSearchBox(String allureRepo){
		mainPage.searchGitHubInput.click();
		mainPage.searchGitHubInput.setValue(allureRepo).pressEnter();
	}
	@Step ("Переходим в репозиторий {allureRepo}")
	public void goToRepository(String allureRepo){
		searchResultPage.repositoryResult(allureRepo).click();
	}
	@Step ("Перейти на вкладку issues")
	public void goIssuesTab(){
		mainApplicationPage.issuesTab.click();
	}
	@Step("Проверить, что на вкладке issues c id={id} отображется ссылка {link}" )
	public void checkIssues(int id,String link){
		issuesPage.issue(id).shouldBe(visible).shouldHave(text(link));
	}
}
