package ru.github.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.github.pages.IssuesPage;
import ru.github.pages.MainApplicationPage;
import ru.github.pages.MainPage;
import ru.github.pages.SearchResultPage;
import ru.github.steps.GitHubSteps;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class IssuingNamInRepositoryTest {
	MainPage mainPage = new MainPage();
	SearchResultPage searchResultPage = new SearchResultPage();
	IssuesPage issuesPage = new IssuesPage();
	MainApplicationPage mainApplicationPage = new MainApplicationPage();
	GitHubSteps gitHubSteps = new GitHubSteps();

	public static final String allureRepo = "eroshenkoam/allure-example";
	public static final String issueName = "issue_to_test_allure_report";
	public static final String baseUrl = "https://github.com/";

	@BeforeAll
	static void beforeAll() {
		Configuration.baseUrl = baseUrl;
		Configuration.pageLoadStrategy = "eager";
		Configuration.browserSize = "1280x1024";
	}

	@Test
	void checkIssueWithListener() {
		SelenideLogger.addListener("allure", new AllureSelenide());
		open("");
		mainPage.searchGitHubInput.click();
		mainPage.searchGitHubInput.setValue(allureRepo).pressEnter();
		searchResultPage.repositoryResult(allureRepo).click();
		mainApplicationPage.issuesTab.click();
		issuesPage.issue(81).shouldBe(visible).shouldHave(text(issueName));
	}

	@Test
	void checkIssueWithLambdaSteps() {
		SelenideLogger.addListener("allure", new AllureSelenide());
		step("Открыть главную страницу + baseUrl", () -> {
			open("");
		});
		step("Ввести в поле поиска " + allureRepo + "  и нажать enter", () -> {
			mainPage.searchGitHubInput.click();
			mainPage.searchGitHubInput.setValue(allureRepo).pressEnter();
		});
		step("Переходим в репозиторий " + allureRepo, () -> {
			searchResultPage.repositoryResult(allureRepo).click();
		});
		step("Перейти на вкладку issues", () -> {
			mainApplicationPage.issuesTab.click();
		});
		step("Проверить, что на вкладке issues отображется ссылка " + issueName, () -> {
			issuesPage.issue(81).shouldBe(visible).shouldHave(text(issueName));
		});
	}

	@Test
	void checkIssueWithSteps() {
		SelenideLogger.addListener("allure", new AllureSelenide());
		gitHubSteps.openMainPage();
		gitHubSteps.enterSearchBox(allureRepo);
		gitHubSteps.goToRepository(allureRepo);
		gitHubSteps.goIssuesTab();
		gitHubSteps.checkIssues(81, issueName);

	}
}
