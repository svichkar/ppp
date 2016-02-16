$(function () {
	var UserModel  = Backbone.Model.extend({
        url: function() {
            return "/user/" + (this.userId ? this.userId : '');
        }
    });
	var UsersCollection = Backbone.Collection.extend({
        model: UserModel,
        url: "users"
    });
	
	 var users = new UsersCollection();
	 
	 var UsersView = Backbone.View.extend({
	        el: $('.content'),
	        template: _.template($('#users').html()),
	        initialize: function () {
	            this.render();
	        },
	        render: function () {
	            setMenu('.users');
	            products.fetch({async: false});
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
	            this.$el.html(this.template({}));
	        },
	        submitForm: function() {
	            var product = new ProductModel();
	            product.set('userName', $('#name').val());
	            product.set('password', $('#password').val());
	            product.set('role', $('#role').val());
	            product.save({async: false});
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
	            this.user = new ProductModel();
	            this.user.set('userId', options.userId);
	            this.user.fetch({async: false});
	            this.render();
	        },
	        render: function () {
	            var model = {"product": this.product.toJSON()};
	            this.$el.html(this.template(model));
	        },
	        submitForm: function() {
	            var user = new UserModel();
	            user.set('userId', $('#userId').val());
	            user.set('userName', $('#userName').val());
	            user.set('password', $('#password').val());
	            user.set('role', $('#role').val());
	            user.save({async: false});
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
	            Backbone.onePage.start();
	        },
	        users: function () {
	            new UsersView();
	        },
	        addUser: function () {
	            new AddProductView();
	        },
	        editUser: function (userId) {
	            new EditProductView({userId: userId});
	        },
	        deleteUser: function(userId) {
	            var user = new UserModel;
	            user.set('userId', userId);
	            user.destroy();
	            window.location.hash = 'users';
	        }
	    });

	    var mainRouter = new MainRouter();
});