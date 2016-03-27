package classified.classifiedbuzzmovieselector.model;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import com.microsoft.windowsazure.mobileservices.*;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
     * Created by Steven on 3/25/2016.
     */
public class DbInterfacer {

    private MobileServiceClient mClient;
    TodoItem item;
    private MobileServiceTable<TodoItem> mToDoTable;

    public DbInterfacer(MobileServiceClient mClient, String tableName) {
        Log.d("LOGIN ACTIVITY", "DbInterfacer");
        item = new TodoItem();
        this.mClient = mClient;
        mToDoTable = mClient.getTable(tableName, TodoItem.class);
        Log.d("LOGIN ACTIVITY", "after table");
    }

    public List<TodoItem> pull() {
        // get list of user Json data
        Log.d("LOGIN ACTIVITY", "pull users");
        try {
            return mToDoTable.execute().get(10, TimeUnit.SECONDS);
        } catch (MobileServiceException e) {
            Log.d("DBINTERFACER", "FAILED TO GET USERS, Interrupted Exception");
        } catch (InterruptedException e) {
            Log.d("DBINTERFACER", "FAILED TO GET USERS, Interrupted Exception");
        } catch (ExecutionException e) {
            Log.d("DBINTERFACER", "FAILED TO GET USERS, Execution exception");
        } catch (TimeoutException e) {
            Log.d("DBINTERFACER", "FAILED TO GET USERS, Timeout Exception");

        }
        Log.d("LOGIN ACTIVITY", "after pull users");
        return null;
    }

    public void updateUser(User user) {
        Gson gson = new Gson();
        item = new TodoItem(user.hashCode() + "", gson.toJson(user));
        try {
            mToDoTable.update(item).get();
        } catch (InterruptedException e) {
            Log.d("DBINTERFACER", "FAILED TO GET USERS, Interrupted Exception");
        } catch (ExecutionException e) {
            Log.d("DBINTERFACER", "FAILED TO GET USERS, Execution exception");
        }
    }

    public void updateRating(UserRating userRating) {
        Gson gson = new Gson();
        item = new TodoItem(userRating.hashCode() + "", gson.toJson(userRating));
        try {
            mToDoTable.update(item).get();
        } catch (InterruptedException e) {
            Log.d("DBINTERFACER", "FAILED TO GET USERS, Interrupted Exception");
        } catch (ExecutionException e) {
            Log.d("DBINTERFACER", "FAILED TO GET USERS, Execution exception");
        }
    }

    public void addUser(User user) {
        Gson gson = new Gson();
        item = new TodoItem(user.hashCode() + "", gson.toJson(user));
        try {
            mToDoTable.insert(item).get(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Log.d("DBINTERFACER", "FAILED TO GET USERS, Interrupted Exception");
        } catch (ExecutionException e) {
            Log.d("DBINTERFACER", "FAILED TO GET USERS, Execution exception");
        } catch (TimeoutException e) {
            Log.d("DBINTERFACER", "FAILED TO GET USERS, Timeout Exception");
        }
    }

    public void addRating(UserRating userRating) {
        Gson gson = new Gson();
        item = new TodoItem(userRating.hashCode() + "", gson.toJson(userRating));
        try {
            mToDoTable.insert(item).get();
        } catch (InterruptedException e) {
            Log.d("DBINTERFACER", "FAILED TO GET USERS, Interrupted Exception");
        } catch (ExecutionException e) {
            Log.d("DBINTERFACER", "FAILED TO GET USERS, Execution exception");
        }
    }

    public class TodoItem {
        private String id;
        private String data;

        public TodoItem(String id, String data) {
            this.id = id;
            this.data = data;
        }

        public TodoItem() {}

        public String getData() {
            return data;
        }
    }
}
