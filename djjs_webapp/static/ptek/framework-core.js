
Test = function(oConfig) {
	var main_variable1 = new main_variable();
	var otestTest = new Ext.form.FormPanel( {
		border :true,
		frame :true,
		width :'100%',
		height :'100%',
		layout :'table',
		renderTo :'combo',
		id :'idMyPortalManageUSerProfileMainPanel',
		layoutConfig : {
			columns :4
		},
		items : [ {
			items :new Ext.form.Label( {
				id :'countryLabel',
				html :'    Country  '
			})
		}, {
			items :main_variable1.country
		}, {
			items :new Ext.form.Label( {
				id :'sateLabel',
				html :'    State  ',
				hidden :true
			})
		}, {
			items :main_variable1.state
		}, {
			colspan :'4',
			html :'<br/><p/>'
		}, {
			colspan :'4',
			items :new Ext.form.Label( {
				id :'show_address',
				html :''
			})
		} ]
	});

	this.view = otestTest;
}