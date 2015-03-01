
var main_variable = function() {

	this.oCountryCodeStoreCombo = new Ext.data.Store( {
		proxy :new Ext.data.HttpProxy( {
			url :'/djjs_webapp/getStates?param=getCountries'
		}),
		reader :new Ext.data.JsonReader( {
			root :'rows',
			totalProperty :'totalCount',
			id :'countrycodecombo',
			fields : [ {
				"name" :"value"
			}, {
				"name" :"name"
			} ]
		}),
		remoteSort :1
	});

	this.country = new Ext.form.ComboBox( {
		store :this.oCountryCodeStoreCombo,
		typeAhead :true,
		forceSelection :true,
		displayField :'name',
		valueField :'value',
		triggerAction :'all',
		id :'countryCodes',
		emptyText :'Select a country...',
		selectOnFocus :true
	});

	this.exchangeStoreProxy = new Ext.data.HttpProxy( {
		url :'/djjs_webapp/getStates?param=getStates&param2='
	});

	this.oExchangeCodeStoreCombo = new Ext.data.Store( {
		proxy :this.exchangeStoreProxy,
		reader :new Ext.data.JsonReader( {
			root :'rows',
			totalProperty :'totalCount',
			id :'exchangecodecombo',
			fields : [ {
				"name" :"value"
			}, {
				"name" :"name"
			} ]
		}),

		remoteSort :1
	});

	Ext.getCmp('countryCodes').on(
			"select",
			function() {
				Ext.getCmp('stateCodes').setValue('Select State');
				Ext.getCmp('sateLabel').show();
				Ext.getCmp('show_address').setText('');
				Ext.getCmp('stateCodes').store.proxy = new Ext.data.HttpProxy( {
					url :'/djjs_webapp/getStates?param=getStates&param2=' + Ext
							.getCmp('countryCodes').getValue()
				});
				Ext.getCmp('stateCodes').store.load();
				Ext.getCmp('stateCodes').show();

			}, this);

	this.state = new Ext.form.ComboBox( {
		store :this.oExchangeCodeStoreCombo,
		typeAhead :true,
		// mode: 'local',
		hidden :true,
		displayField :'name',
		valueField :'value',
		id :'stateCodes',
		forceSelection :true,
		triggerAction :'all',
		emptyText :'Select a state...',
		loadingText :'loading...',
		selectOnFocus :true
	});

	this.proxy = new Ext.data.HttpProxy( {
		url :'/djjs_webapp/getStates'
	});

	this.grid_store = new Ext.data.Store( {

		proxy :this.proxy,

		reader :new Ext.data.JsonReader( {
			root :"rows",
			id :'grid_store_id',
			fields : [ {
				name :'description'
			}, {
				name :'ashramID'
			}, {
				name :'name'
			}, {
				name :'phone'
			}, {
				name :'zip'
			}, {
				name :'headedBy'
			}, {
				name :'stateID'
			} ]
		}),

		remoteSort :1
	});

	this.onFailure = function() {
		alert('Seems like something gone wrong. Please try again or contact administrator.');
	}

	/***************************************************************************
	 * 
	 * GRID CODE
	 * 
	 **************************************************************************/

	// create the Grid
	this.grid = new Ext.grid.GridPanel( {
		store :this.grid_store,
		columns : [ {
			id :'ashramID',
			header :'ashramID',
			sortable :true,
			dataIndex :'ashramID'
		}, {
			id :'name',
			header :'Name',
			sortable :true,
			dataIndex :'name'
		}, {
			id :'description',
			header :'Address',
			sortable :true,
			dataIndex :'description'
		}, {
			id :'phone',
			header :'phone',
			sortable :true,
			dataIndex :'phone'
		}, {
			id :'zip',
			header :'Postal code',
			sortable :true,
			dataIndex :'zip'
		}, {
			id :'headedBy',
			header :"Coordinator",
			sortable :true,
			dataIndex :'headedBy'
		}/*, {
			id :'stateID',
			header :"stateID",
			sortable :true,
			dataIndex :'stateID'
		}*/

		],
		stripeRows :true,
		autoExpandColumn :'description',
		height :350,
		width :'100%',
		title :'Addresses'
	});

	Ext.getCmp('stateCodes').on("select", function() {
		this.grid_store.load( {
			params : {
				param :'getAddress',
				param2 :Ext.getCmp('stateCodes').getValue()
			}
		});
	}, this);

	this.grid.render('contact-us-grid');

}
