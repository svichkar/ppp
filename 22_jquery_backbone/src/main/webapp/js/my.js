$(function() {
	var CarModel = Backbone.Model.extend({
		url : function() {
			return "/rest/car/" + (this.id ? this.id : '');
		}
	});

	var CarCollection = Backbone.Collection.extend({
		model : CarModel,
		url : '/rest/car/all'
	});

	var cars = new CarCollection();

	var CarsView = Backbone.View.extend({
		el : $('.content'),
		template : _.template($('#cars').html()),
		initialize : function() {
			this.render();
		},
		render : function() {
			setMenu('.products');
			cars.fetch({
				async : false
			});
			var models = {
				"cars" : cars.toJSON()
			};
			this.$el.html(this.template(models));
		}
	});
	var MainRouter = Backbone.Router.extend({
		routes : {
			"" : "cars",
			"cars" : "cars",
			"addCar" : "addCar",
			"editCar/:id" : "editCar",
			"deleteCar/:id" : "deleteCar"
		},
		initialize : function() {
			Backbone.my.start();
		},
		cars : function() {
			new CarsView();
		}
	});

});