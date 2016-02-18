_.templateSettings = {
	evaluate : /\{\{(.+?)\}\}/g,
	interpolate : /\{\{=(.+?)\}\}/g,
	escape : /\{\{-(.+?)\}\}/g
}

function setMenu(menuItem) {
	$('li').removeClass('active');
	$(menuItem).addClass('active');
}
$(function() {
	var CarModel = Backbone.Model.extend({
		url : function() {
			return '/spring/rest/car/' + (this.id ? this.id : '');
		}
	});
	
	var CarCollection = Backbone.Collection.extend({
		model : CarModel,
		url : '/spring/rest/car/all'
	});
	
	var CustomerModel = Backbone.Model.extend({
		url : function() {
			return '/spring/rest/customer/' + (this.id ? this.id : '');
		}
	});

	var CustomerCollection = Backbone.Collection.extend({
		model : CustomerModel,
		url : '/spring/rest/customer/all'
	});

	var cars = new CarCollection();
	var customers = new CustomerCollection();

	var CarsView = Backbone.View.extend({
		el : $('.content'),
		template : _.template($('#cars').html()),
		initialize : function() {
			this.render();
		},
		render : function() {
			cars.fetch({
				async : false,
			});
			var models = {
				"cars" : cars.toJSON()
			};
			this.$el.html(this.template(models));
		}
	});
	
	var EditCarView = Backbone.View.extend({
		el : $('.content'),
		events : {
			'click #updateSbmt' : 'submitForm'
		},
		template : _.template($('#editCar').html()),
		initialize : function(options) {
			this.car = new CarModel();
			this.car.set('id', options.id);
			this.car.fetch({
				async : false
			});
			this.render();
		},
		render : function() {
			var model = {
				"car" : this.car.toJSON()
			};
			this.$el.html(this.template(model));
		},
		submitForm : function() {
			var car = new CarModel();
			car.set('id', $('#id').val());
			car.set('model', $('#model').val());
			car.set('vin', $('#vin').val());
			car.set('description', $('#description').val());
			car.set('customerId', $('#customerId').val());
			car.save();
			window.location.hash = 'cars';
		}
	});
	
	var AddCarView = Backbone.View.extend({
        el: $('.content'),
        events: {
            'click #addSbmt': 'submitForm'
        },
        template: _.template($('#addCar').html()),
        initialize: function () {
        	customers.fetch({
				async : false,
			});
            this.render();
        },
        render: function () {
        	var model = {
    				"customers" : customers.toJSON()
    			};
            this.$el.html(this.template(model));
        },
        submitForm: function() {
            var car = new CarModel();
            car.set('model', $('#model').val());
			car.set('vin', $('#vin').val());
			car.set('description', $('#description').val());
			car.set('customerId', $('#customerId').val());
			car.save();
            window.location.hash = 'cars';
//			var updatedUrl = window.location.href.replace('#addCar','?destination=BackBone');
//			router.navigate(updatedUrl, {trigger: true, replace: true});
//			history.pushState("", "BackBone", updatedUrl);
//			var updatedUrl = window.location.href.concat('?destination=BackBone');
//			window.open(updatedUrl);
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
			Backbone.history.start();
		},

		cars : function() {
			new CarsView();
		},

		editCar : function(id) {
			new EditCarView({id: id});
		},
		
		addCar: function() {
			new AddCarView();
		},
		deleteCar: function(id) {
            var car = new CarModel;
            car.set('id', id);
            car.destroy();
            window.location.hash = 'cars';
        }
		
	});

	var mainRouter = new MainRouter();

});
