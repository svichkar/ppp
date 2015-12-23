var CarCollection = Backbone.Collection.extend({
	model: Car,
	url: '/services/car/getAll'
});