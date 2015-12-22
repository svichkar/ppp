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

	var CarModel = Backbone.Model.extend({
		initialize : function() {
			console.log('Car created.');
		},
		url_root : '/services/car',
		defaults : {
			id : '',
			description : '',
			model : '',
			vin : '',
			customer_id : null
		},
		/*
		 * fetchCurrent : function(id, options) { options = options || {}; if
		 * (options.url === undefined) { options.url = this.urlRoot +
		 * "/getById?carId=" + id; } return
		 * Backbone.Model.prototype.fetch.call(this, options); }
		 */
		getCustomUrl : function(method) {
			switch (method) {
			case 'read':
				return this.url_root + '/getById?carId=' + this.id;
				break;
			case 'create':
				return this.url_root + '/add';
				break;
			case 'update':
				return this.url_root + '/update';
				break;
			case 'delete':
				return this.url_root + '/delete';
				break;
			}
		},
		sync : function(method, model, options) {
			options || (options = {});
			options.url = this.getCustomUrl(method.toLowerCase());
			return Backbone.sync.apply(this, arguments);
		}
	});

	var CarCollection = Backbone.Collection.extend({
		model : CarModel,
		url : '/services/car/getAll'
	});

	var AllCarsView = Backbone.View.extend({
		template : 'carsPage',
		render : function() {
			var that = this;
			$.get("/WEB-INF/jsp/" + this.template + ".jsp", function(template) {
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