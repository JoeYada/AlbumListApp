package com.challenge.albumlistapp;

import com.challenge.albumlistapp.view.album_list.AlbumListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.challenge.albumlistapp.TestUtil.atPosition;

/**
 * Instrumented test, which will execute on an Android device.
 *
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class AlbumsScreenTest {

    @Rule
    public ActivityTestRule<AlbumListActivity> albumListActivityActivityTestRule =
            new ActivityTestRule<>(AlbumListActivity.class);

    // More complex tests should be added as app's complexity rises
    @Test
    public void displayItemsInList(){
        // check if the ListView is visible
        onView(withId(R.id.album_list_recycler_view)).check(matches(isDisplayed()));
    }

    @Test
    public void scrollRecyclerViewToBottom() {
        RecyclerView recyclerView = albumListActivityActivityTestRule.getActivity().findViewById(R.id.album_list_recycler_view);
        onView(withId(R.id.album_list_recycler_view))
                .perform(RecyclerViewActions.scrollToPosition(recyclerView.getAdapter().getItemCount() -1));
    }

    @Test
    public void checkItemAtPosition() {
        onView(withId(R.id.album_list_recycler_view))
                .check(matches(atPosition(2, hasDescendant(withText("User ID:")))));
    }

}
