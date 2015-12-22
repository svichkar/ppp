document.onreadystatechange = function() {
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
}