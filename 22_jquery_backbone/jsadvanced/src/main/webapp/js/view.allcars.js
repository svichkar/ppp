var AllCarsView = Backbone.View.extend({
	el: $('#content'),
	model: CarCollection,
	tagName: 'allCars',
	className: 'content',
	template: $('#allcars-content').html(),
	
	initialize: function() {
		this.collection = 
	},
	
	render: function() {
		var tmpl = _.template(this.template);
		
		this.$el.html(tmpl(this.model.toJSON()));
		return this;
	}
});