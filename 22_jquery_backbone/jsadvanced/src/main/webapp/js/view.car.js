var CarView = Backbone.View.extend({
	model: CarModel,
	render: function() {
		this.$el.html(this.template(this.model.attributes));
		
		return this;
	}
});