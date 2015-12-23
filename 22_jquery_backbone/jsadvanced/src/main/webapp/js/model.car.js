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
	idAttribute : 'id',
	getCustomUrl : function(method) {
		switch (method) {
		case 'read':
			return this.url_root + '/' + this.id;
			break;
		default:
			return this.url_root;
			break;
		}
	},
	sync : function(method, model, options) {
		options || (options = {});
		options.url = this.getCustomUrl(method.toLowerCase());
		return Backbone.sync.apply(this, arguments);
	},
	constructor : function(attributes, options) {
		Backbone.apply(this, attributes);
	}
});