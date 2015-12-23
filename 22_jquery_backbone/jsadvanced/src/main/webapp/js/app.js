var AppRouter = Backbone.Router.extend({
	routes : {
		'' : 'allCars',
		'!/allCars' : 'allCars',
		'!/addCar' : 'add',
		'!/editCar' : 'edit',
		'!/deleteCar' : 'delete'
	},

	allCars : function() {
		$('.block').hide();
		$('#allCars').show();
	}

//

});

var app_router = new AppRouter;
Backbone.history.start();