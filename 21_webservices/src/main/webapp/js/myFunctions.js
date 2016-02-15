function sendAjaxCreate() {
    var role = new Object();
    role.roleId = $('#roleId').val();
    var user = new Object();
    user.userId = $('#userId').val();
    user.login = $('#login').val();
    user.password = $('#password').val();
    user.role = role;

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
function sendAjaxEdit(elem) {
    var form = $(elem).parent().parent();
    var role = new Object();
    role.roleId = form.find('.roleId:first').val();
    var user = new Object();
    user.userId = form.find('.userId:first').val();
    user.login = form.find('.login:first').val();
    user.password = form.find('.password:first').val();
    user.role = role;

    $.ajax({
        url: "http://localhost:8080/Jersey/rest/user/update",
        type: 'PUT',
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
function sendAjaxDelete(elem) {
    var form = $(elem).parent().parent();
    var role = new Object();
    role.roleId = form.find('.roleId:first').val();
    var user = new Object();
    user.userId = form.find('.userId:first').val();
    user.login = form.find('.login:first').val();
    user.password = form.find('.password:first').val();
    user.role = role;

    $.ajax({
        url: "http://localhost:8080/Jersey/rest/user/delete",
        type: 'DELETE',
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