class ReplyAppStateRestorationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
}

...
@Test
fun compactDevice_selectedEmailEmailRetained_afterConfigChange() {
    // Setup compact window
    val stateRestorationTester = StateRestorationTester(composeTestRule)
    stateRestorationTester.setContent { ReplyApp(windowSize = WindowWidthSizeClass.Compact) }

    // Given third email is displayed
    composeTestRule.onNodeWithText(
        composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
    ).assertIsDisplayed()

    // Open detailed page
    composeTestRule.onNodeWithText(
        composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].subject)
    ).performClick()

    // Verify that it shows the detailed screen for the correct email
    composeTestRule.onNodeWithContentDescriptionForStringId(
        R.string.navigation_back
    ).assertExists()
    composeTestRule.onNodeWithText(
        composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
    ).assertExists()

    // Simulate a config change
    stateRestorationTester.emulateSavedInstanceStateRestore()

    // Verify that it still shows the detailed screen for the same email
    composeTestRule.onNodeWithContentDescriptionForStringId(
        R.string.navigation_back
    ).assertExists()
    composeTestRule.onNodeWithText(
        composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
    ).assertExists()
}

@Test
fun expandedDevice_selectedEmailEmailRetained_afterConfigChange() {
    // Setup expanded window
    val stateRestorationTester = StateRestorationTester(composeTestRule)
    stateRestorationTester.setContent { ReplyApp(windowSize = WindowWidthSizeClass.Expanded) }

    // Given third email is displayed
    composeTestRule.onNodeWithText(
        composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].body)
    ).assertIsDisplayed()

    // Select third email
    composeTestRule.onNodeWithText(
        composeTestRule.activity.getString(LocalEmailsDataProvider.allEmails[2].subject)
    ).performClick()
    ...
}
