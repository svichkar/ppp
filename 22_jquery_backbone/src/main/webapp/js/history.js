_.templateSettings = {
    evaluate: /\{\{(.+?)\}\}/g,
    interpolate: /\{\{=(.+?)\}\}/g,
    escape: /\{\{-(.+?)\}\}/g
}

$(function() {
	var TermModel = Backbone.Model.extend({
		url : function() {
			return "/22_jquery_backbone-0.0.1-SNAPSHOT/rest/term/findById/" + (this.id ? this.id : '');
		}
	});

	var TermsCollection = Backbone.Collection.extend({
		model : TermModel,
		url : '/22_jquery_backbone-0.0.1-SNAPSHOT/rest/term/findAll'
	});

	var terms = new TermsCollection();

	var TermsView = Backbone.View.extend({
		el : $('.content'),
		template : _.template($('#terms').html()),
		initialize : function() {
			this.render();
		},
		render : function() {
			terms.fetch({
				async : false
			});
			var models = {
				"terms" : terms.toJSON()
			};
			this.$el.html(this.template(models));
		}
	});
	
	var AddTermView = Backbone.View.extend({
        el: $('.content'),
        events: {
            'click #add': 'submitForm'
        },
        template: _.template($('#addTerm').html()),
        initialize: function () {
            this.render();
        },
        render: function () {
            this.$el.html(this.template({}));
        },
        submitForm: function() {
            var term = new TermModel();
            term.set('termName', $('#termName').val());
            term.save();
            window.location.hash = 'terms';
        }
    });
    
	
	
	var MainRouter = Backbone.Router.extend({
		routes : {
			"": "terms",
            "terms": "terms",
            "addTerm": "addTerm"
		},
		initialize : function() {
			Backbone.history.start();
		},
		terms : function() {
			new TermsView();
		},
		addTerm: function () {
            new AddTermView();
        }
	});
	
	var mainRouter = new MainRouter();

});