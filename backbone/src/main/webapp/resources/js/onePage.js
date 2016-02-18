function setMenu(menuItem) {
    $('li').removeClass('active');
    $(menuItem).addClass('active');
}
$(function () {
	var UserModel  = Backbone.Model.extend({
        url: function() {
            return "/backbone/rest/user/" + (this.id ? this.id : '');
        }
    });
	var UsersCollection = Backbone.Collection.extend({
        model: UserModel,
        url: "/backbone/rest/user/getAll"
    });
	
	var RoleModel = Backbone.Model.extend({
		url: function(){
			return "/backbone/rest/role/" + (this.id ? this.id : '');
		}
	});
	
	var RolesCollection = Backbone.Collection.extend({
        model: RoleModel,
        url: "/backbone/rest/role/getAllEntity"
    });
	
	var roles = new RolesCollection();
	
	 var users = new UsersCollection();
	 
	 var UsersView = Backbone.View.extend({
	        el: $('.content'),
	        template: _.template($('#users').html()),
	        initialize: function () {
	            this.render();
	        },
	        render: function () {
	            setMenu('.users');
	            users.fetch({async: false});
	            var models = {"users": users.toJSON()};
	            this.$el.html(this.template(models));
	        }
	    });
	 
	 var AddUserView = Backbone.View.extend({
	        el: $('.content'),
	        events: {
	            'click #addSbmt': 'submitForm'
	        },
	        template: _.template($('#addUser').html()),
	        initialize: function () {
	            this.render();
	        },
	        render: function () {
	            setMenu('.addUser');
	            roles.fetch({async: false});
	            var models = {"roles": roles.toJSON()};
	            this.$el.html(this.template(models));
	        },
	        submitForm: function() {
	            var user = new UserModel();
	            var role = new RoleModel();
	            user.set('userName', $('#userName').val());
	            user.set('password', $('#password').val());
	            role.set('id', parseInt($('#role').val()));
	            role.fetch({async: false});
	            user.set('role', role);
	            user.save();
	            window.location.hash = 'users';
	        }
	    });
	 
	 var EditUserView = Backbone.View.extend({
	        el: $('.content'),
	        events: {
	            'click #updateSbmt': 'submitForm'
	        },
	        template: _.template($('#editUser').html()),
	        initialize: function (options) {
	            this.user = new UserModel();
	            this.roles = new RolesCollection();
	            this.roles.fetch({async: false});
	            this.user.set('id', options.userId);
	            this.user.fetch({async: false});	
	            this.render();
	        },
	        render: function () {
	            var model = {"user": this.user.toJSON(),
	            		"roles": this.roles.toJSON()};
	            this.$el.html(this.template(model));
	        },
	        submitForm: function() {
	            var user = new UserModel();
	            var role = new RoleModel();
	            user.set('id', $('#id').val());
	            user.set('userName', $('#userName').val());
	            user.set('password', $('#password').val());
	            role.set('id', $('#role').val());
	            role.fetch({async: false});
	            user.set('role', role);
	            user.save();
	            window.location.hash = 'users';
	        }
	    });
	 
	 var MainRouter = Backbone.Router.extend({
	        routes: {
	            "": "users",
	            "users": "users",
	            "addUser": "addUser",
	            "editUser/:userId": "editUser",
	            "deleteUser/:userId": "deleteUser"
	        },
	        initialize: function () {
	            Backbone.history.start();
	        },
	        users: function () {
	            new UsersView();
	        },
	        addUser: function () {
	            new AddUserView();
	        },
	        editUser: function (userId) {
	            new EditUserView({userId: userId});
	        },
	        deleteUser: function(userId) {
	            var user = new UserModel;
	            user.set('id', userId);
	            user.destroy({async: false});
	            window.location.hash = 'users';
	        }
	    });

	    var mainRouter = new MainRouter();
});