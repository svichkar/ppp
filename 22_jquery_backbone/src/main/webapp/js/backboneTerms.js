_.templateSettings = {
    evaluate: /\{\{(.+?)\}\}/g,
    interpolate: /\{\{=(.+?)\}\}/g,
    escape: /\{\{-(.+?)\}\}/g
};

$(function() {
	var TermModel = Backbone.Model.extend({
		toJSON: function(options) {
	        var attr = _.clone(this.attributes);
	        delete attr.id;
	        return attr;
	    },
		url : function() {
			return '/22_jquery_backbone-0.0.1-SNAPSHOT/rest/term/' + (this.id ? this.id : '');
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
    
    var EditTermView = Backbone.View.extend({
        el: $('.content'),
        events: {
            'click #update': 'submitForm'
        },
        template: _.template($('#editTerm').html()),
        initialize: function (options) {
            this.term = new TermModel();
            this.term.set('id', options.termId);
            this.term.set('termId', options.termId);
            this.term.fetch({async: false});
            this.render();
        },
        render: function () {
            var model = {"term": this.term.toJSON()};
            this.$el.html(this.template(model));
        },
        submitForm: function() {
            var term = new TermModel();
            term.set('termId', $('#termId').val());
            term.set('termName', $('#termName').val());
            term.set('id', $('#termId').val());
            term.save();
            window.location.hash = 'terms';
        }
    });
	
	var MainRouter = Backbone.Router.extend({
		routes : {
			"": "terms",
            "terms": "terms",
            "addTerm": "addTerm",
            "editTerm/:termid": "editTerm",
            "deleteTerm/:termid": "deleteTerm"
		},
		initialize : function() {
			Backbone.history.start();
		},
		terms : function() {
			new TermsView();
		},
		addTerm: function () {
            new AddTermView();
        },
        editTerm: function (termId) {
            new EditTermView({termId: termId});
        },
        deleteTerm: function(termId) {
            var term = new TermModel;
            term.set('id', termId);
            term.destroy();
            window.location.hash = 'terms';
        }
		
	});
	
	var mainRouter = new MainRouter();

});