package utilities;

public class EndPoints {

    public static final String uri = "https://gorest.co.in";
    public static final String postUriCreateUser = "/public/v2/users";
    public static final String getUserDetails="/public/v2/users/";
    public static final String putUriUpdateUser = "/public/v2/users/";//need to pass {id} with request
    public static final String deleteUriDeleteUser = "/public/v2/users/{id}";
    public static final String postUriCreatePostUri = "/public/v2/users/{id}/posts";
    public static final String postUriCreatePostComment = "/public/v2/posts/{id}/comments";
    public static final String postUriCreateUserToDo = "/public/v2/users/{id}/todos";
    public static final String Token="76604320acb951fdbf6329192ec375a3dc97420fca3d49ca83e9f3579b450247";
    public static final Integer id=7803076;

}