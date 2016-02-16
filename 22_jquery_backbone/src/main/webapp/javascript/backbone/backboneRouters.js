var Workspace = Backbone.Router.extend({

routes: {
            "": "students",
            "students": "students",
            "addStudent": "addStudent",
            "editStudent/:id": "editStudent",
            "deleteStudent/:id": "deleteStudent"
},

initialize: function () {
             Backbone.history.start();
         },
         products: function () {
             new ProductsView();
         },
         addProduct: function () {
             new AddProductView();
         },
         editProduct: function (id) {
             new EditProductView({id: id});
         },
         deleteProduct: function(id) {
             var product = new ProductModel;
             product.set('id', id);
             product.destroy();
             window.location.hash = 'products';
         }
});

var mainRouter = new Workspace();