function sendAjax() {
    var user = new Object();
    user.userId = $('#userId').val();
    user.login = $('#login').val();
    user.password = $('#password').val();
    user.roleId = $('#roleId').val();

    $.ajax({
        url: "http://localhost:8080/Jersey/rest/user/create",
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(user),
        contentType: 'application/json',
        mimeType: 'application/json',

        success: function (data) {
            location.reload();
            },
        error:function(data,status,er) {
            alert("error: "+data+" status: "+status+" er:"+er);
        }
    });
}