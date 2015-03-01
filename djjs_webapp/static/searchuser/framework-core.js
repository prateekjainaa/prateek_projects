

Test = function(oConfig){
var main_variable1 = new adduserImpl();
otestTest = new Ext.form.FormPanel({
            border :true,
            standardSubmit : true,
	    frame :true,
	    //width: '600px',
	    layout: 'table',
	    renderTo: 'combo',
	    method: 'POST',
	    url: '/djjs_webapp/search',
	    id :'searchUserPanel',
	    layoutConfig: {
	    		columns: 2
	    		
		},
		defaults:{padding :"10px"},
	    items :[{
	            	colspan:'2',
	            	style: {
	            		textAlign:'center'
	            	    },
	              html : 'SEARCH...'  
	            },{ 
	            	items: main_variable1.firstName
	            },{
	            	items: main_variable1.firstNameText
	            },{ 
	            	items: main_variable1.lastName
	            },{
	            	items: main_variable1.lastNameText
	            },{
	           	 items: main_variable1.sex
	            },{
	            },{
	            	items: main_variable1.dob
	            },{
	             	items: main_variable1.addDP
	            },{
	            	items: main_variable1.betweenLabel
	            },{
	             	items: main_variable1.maxdob
	            },{
	            	html:''             	            
	            },{
	                items: main_variable1.address
	            },{
	            	items: main_variable1.countryLabel
	            },{
	            	items: main_variable1.country
	            },{
	            	items: main_variable1.stateName
	            },{
	            	items: main_variable1.state
	            },{
	            	items: main_variable1.district
	            },{
	                items: main_variable1.districts
	            },{
	            	items: main_variable1.town
	            },{
	            	items: main_variable1.townText
	            },{
	            	items: main_variable1.phone_mobile_label
	            },{
	            	items: main_variable1.phone_mobile_text
	            },{
	            	items: main_variable1.phone_ll_label
	            },{
	            	items: main_variable1.phone_ll_text
	            },{
	            	items: main_variable1.email_label
	            },{
	            	items: main_variable1.email_text
	            },{
	            	items: main_variable1.related_to_label
	            },{
	            	items: main_variable1.related_to_text
	            },{
	            	items: main_variable1.sex
	            },{
	            	items: main_variable1.selectsex
	            },{
	            	items: main_variable1.vip_label
	            },{
	            	items: main_variable1.vip
	            },{
	            	items: main_variable1.occup_catg_label
	            },{
	            	items: main_variable1.occup_catg
	            },{
	            	items: main_variable1.status_label
	            },{
	            	items: main_variable1.swami
	            },{
	            	items: main_variable1.deeksha_date_label
	            },{
	            	items: main_variable1.deeksha_date_value
	            },{
	            	items: main_variable1.betweenLabel2
	            },{
	             	items: main_variable1.maxddate
	            },{
	            	items: main_variable1.qualification_label
	            },{
	            	items: main_variable1.qualificationGrid
	            },{
	            	html:''
	            },{
	            	items: main_variable1.qualification_text
	            },{
	            	items: main_variable1.occupation_label
	            },{
	            	items: main_variable1.occupation_text
	            },{
	            	items: main_variable1.sewaLabel
	            },{
	            	items: main_variable1.sewaGrid
	            },{
	            	html:''
	            },{
	            	items: main_variable1.sewaSelectionText
	            },
	            
	            {
	            	items: main_variable1.other_prof_label
	            },{
	            	items: main_variable1.other_prof_text
	            },{
	               colspan:'2',
	               style: {
		             		align:'center'
	            	    },
	            	buttons: [
				    	{
					text: 'Search',
					formBind: true,
					//scope : otestTest,
					handler: function() {
					// sewa haindling -- start
					var sSelection = main_variable1.sewaGrid.getSelectionModel().getSelections();
					var sewaIds = "";
					for(key in sSelection) {					 
					 if(sSelection[key] instanceof Function) {
					 	continue;
					 }
					 sewaIds = sewaIds + sSelection[key].get("value") + ",";
					}
					var sewaLength = sewaIds.length;
					if(sewaLength > 0) {
					    main_variable1.sewaSelectionText.setValue(sewaIds.substring(0, sewaLength-1));
					}
					// sewa haindling -- End
					
					var qaSelection = main_variable1.qualificationGrid.getSelectionModel().getSelections();
										var qaIds = "";
										for(qakey in qaSelection) {					 
										 if(qaSelection[qakey] instanceof Function) {
										 	continue;
										 }
										 qaIds = qaIds + qaSelection[qakey].get("value") + ",";
										}
										var qaLength = qaIds.length;
										if(qaLength > 0) {
										    main_variable1.qualification_text.setValue(qaIds.substring(0, qaLength-1));
					}
					
					// qa haindling -- start
					
					// sewa haindling -- end
					otestTest.getForm().getEl().dom.action = '/djjs_webapp/search';
					otestTest.getForm().submit();
			               }
			               },
			               {
			               text: 'Reset',
				       	formBind: true,
					handler: function() {
					      otestTest.getForm().reset();
					}
			               
			               
	    }]
	            }]
	    
	    

	});
	
	this.view = otestTest;
}