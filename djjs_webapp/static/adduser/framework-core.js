

Test = function(oConfig){
var main_variable1 = new adduserImpl();
var otestTest = new Ext.form.FormPanel({
            fileUpload: true,
	    border :true,
	    frame :true,
	    //width: '600px',
	    layout: 'table',
	    renderTo: 'combo',
	    url: '/djjs_webapp/addUser',
	    id :'addUserPanel',
	    layoutConfig: {
	    		columns: 2
	    		
		},
		defaults:{padding :"10px"},
	    items :[{
	            	colspan:'2',
	            	style: {
	            		textAlign:'center'
	            	    },
	              html : 'Add USER INFO.'  
	            },{ 
	            	items: main_variable1.firstName
	            },{
	            	items: main_variable1.firstNameText
	            },{ 
	            	items: main_variable1.lastName
	            },{
	            	items: main_variable1.lastNameText
	            },{ 
	            	items: main_variable1.guardianName
	            },{
	           	 items: main_variable1.gaurdianNameText
	            },{
	           	 items: main_variable1.sex
	            },{
	            },{
	            	items: main_variable1.dob
	            },{
	             	items: main_variable1.addDP
	            },{
	            	items: main_variable1.photo
	            },{
	            	items: main_variable1.imgupload             	            
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
	            	items: main_variable1.fullAdd
	            },{
	            	items: main_variable1.fullAddText
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
	            	items: main_variable1.status_label
	            },{
	            	items: main_variable1.swami
	            },{
	            	items: main_variable1.deeksha_aashram_label
	            },{
	            	items: main_variable1.deeksha_aashram_text
	            },{
	            	items: main_variable1.deeksha_date_label
	            },{
	            	items: main_variable1.deeksha_date_value
	            },{
	            	items: main_variable1.qualification_label
	            },{
	            	items: main_variable1.qualification_text
	            },{
	            	items: main_variable1.occupation_label
	            },{
	            	items: main_variable1.occupation_text
	            },{
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
					text: 'Save',
					formBind: true,
					handler: function() {
					otestTest.getForm().submit({
					method: 'POST',
					waitTitle: 'Adding...',
					waitMsg: 'Sending data...',
					
					success: function(req,res) {
					Ext.Msg.alert('Status', res.result);
			                },
			                
			                failure: function(form, action) {
					if (action.failureType == 'server') {
					Ext.Msg.alert('Additon Failed!', 'server failed to add.');
					} else {
					Ext.Msg.alert('Warning!', 'server is unreachable : ' );
					}
					
					}
					});
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