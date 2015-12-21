var AppRouter = Backbone.Router.extend({
	routes: {
		//
	}
});

var app_router = new AppRouter;
app_router.on('route:getPost', function(id) {
	
});
Backbone.history.start();



var CarModel = Backbone.Model.extend({
	initialize: function() {
		console.log('Car created.');
	},
	url_root: '/services/car',
	defaults: {
		car_id: '',
		description: '',
		model: '',
		vin: '',
		customer_id: null
	},
	fetchCurrent: function (id, options) {
        options = options || {};
        if (options.url === undefined) {
            options.url = this.urlRoot + "?carId=" + id;
        }
        return Backbone.Model.prototype.fetch.call(this, options);
    }
});

