document.onreadystatechange = function() {
	var AppRouter = Backbone.Router.extend({
		viewAll : null,
		viewOne : null,
		routes : {
			"/admin/addCar.do" : "add",
			"/admin/editCar.do" : "edit",
			"/allCars" : "all"
		// "/deleteCar.do": "delete"
		},
		initialize : function() {
			this.viewAll = new AllCarsView();
			this.viewOne = new CarView();
		}
	});

	var app_router = new AppRouter;
	/*
	 * app_router.on('route:getPost', function(id) {
	 * 
	 * });
	 */
	Backbone.history.start();

	

	var CarCollection = Backbone.Collection.extend({
		model : CarModel,
		url : '/services/car/getAll'
	});

	var AllCarsView = Backbone.View.extend({
		template : $('#type-template').html(),
		render : function() {
			var that = this;
			$.get('.block', function(template) {
				var html = $(template).tmpl();
				that.$el.html(html);
			});
			return this;
		}
	});
	//
	var CarView = Backbone.View.extend({
		template : 'car',
		initialize : function(options) {
			this.options = options || {};
		},
		render : function() {
			var that = this;
			$.get("/WEB-INF/jsp/" + this.template + ".jsp", function(template) {
				var html = $(template).tmpl();
				that.$el.html(html);
			});
			return this;
		}
	});
}