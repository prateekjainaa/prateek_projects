


var adduserImpl = function() {

this.firstName = new Ext.form.Label({
			id:'firstNameLabel',
	                 html:'    First Name  ' 
                         });
                         
 this.lastName = new Ext.form.Label({
 			id:'lastNameLabel',
 	                 html:' Last Name  ' 
                         });
                              
 this.guardianName = new Ext.form.Label({
			id:'guardianNameLabel',
	                 html:' Guardian Name  ' 
                         });  
                             
 this.sex = new Ext.form.Label({
  			id:'sexLabel',
  	                 html:'  Sex  ' 
                         }); 
  
  this.dob = new Ext.form.Label({
    			id:'dobLabel',
    	                 html:'  D.O.B  ' 
                         });
                         
 this.age = new Ext.form.Label({
     			id:'ageLabel',
     	                 html:'  Age  ' 
                         });
                       
 this.photo = new Ext.form.Label({
     			id:'imgLabel',
     	                 html:'  Image  ' 
                         });
                         
                         
 this.address = new Ext.form.Label({
     			id:'addressLabel',
     	                 html:' Address  '     	                 
                         });

this.countryLabel = new Ext.form.Label({
    			id:'countryLabel',
    	                 html:' Country ' 
                         });
                         
this.stateName = new Ext.form.Label({
    			id:'stateLabel',
    	                 html:' State ' 
                         });
                         
this.district = new Ext.form.Label({
     			id:'districtLabel',
     	                 html:'  District  ' 
                         });

this.firstNameText = new Ext.form.TextField({
                          id: 'firstNameText',
                          name: 'firstNameText',
                          allowBlank:true
                          });
                          
 this.lastNameText = new Ext.form.TextField({
                           id: 'lastNameText',
                           name: 'lastNameText',
                           allowBlank:true
                          });
                          
                          
                          
this.town = new Ext.form.Label({
     			id:'townLabel',
     	                 html:'  Town/Village  ' 
                         });

this.townText = new Ext.form.TextField({
                          id: 'townNameText',
                          name: 'townNameText'
                          });


this.fullAdd = new Ext.form.Label({
     			id:'fullAddLabel',
     	                 html:'  Full Postal Address  ' 
                         });

this.fullAddText = new Ext.form.TextArea({
                          id: 'fullAddText',
                          name: 'fullAddText',
                          emptyText:'Provide Full Postal Address',
                          width: 280,
                          height: 100
                          });


                          
 
 this.gaurdianNameText = new Ext.form.TextField({
                           id: 'gaurdianNameText',
                           name: 'gaurdianNameText',
                           allowBlank:true
                          });

this.addDP = new Ext.ux.form.XDateField({
		            id :'toDateFieldId',
		            name :'dateOfBirth'
		        });

this.oCountryCodeStoreCombo = new Ext.data.Store({
		proxy: new Ext.data.HttpProxy({
		url:'/djjs_webapp/getStates?param=getCountries'
		}),
		reader: new Ext.data.JsonReader({
		root: 'rows',
		totalProperty: 'totalCount',
		id: 'countrycodecombo',
		fields:[{"name":"value"},{"name":"name"}]
		}),
		remoteSort : 1
	});

    this.country = new Ext.form.ComboBox({
        store: this.oCountryCodeStoreCombo,
        typeAhead: true,
        forceSelection: true,
        displayField: 'name',
	valueField: 'value',
	hiddenName: 'country_hidden_name',
        triggerAction: 'all',
        id: 'countryCodes',
        name: 'countryCodes',
        emptyText:'Select a country...',
        selectOnFocus:true
    });


this.exchangeStoreProxy = new Ext.data.HttpProxy({
    	url:'/djjs_webapp/getStates?param=getStates&param2='
	});
	
this.oExchangeCodeStoreCombo = new Ext.data.Store({
		proxy: this.exchangeStoreProxy,
		reader: new Ext.data.JsonReader({
		root: 'rows',
		totalProperty: 'totalCount',
		id: 'exchangecodecombo',
		fields:[{"name":"value"},{"name":"name"}]
		}),
		
		remoteSort : 1
	});

Ext.getCmp('countryCodes').on("select", function(){
		Ext.getCmp('stateCodes').setValue('Select State');
			
	Ext.getCmp('stateCodes').store.proxy = new Ext.data.HttpProxy({ url: '/djjs_webapp/getStates?param=getStates&param2='+Ext.getCmp('countryCodes').getValue()
					});
		Ext.getCmp('stateCodes').store.load();
		Ext.getCmp('stateCodes').show();
		
	},this);
	
	



this.state = new Ext.form.ComboBox({
        store: this.oExchangeCodeStoreCombo,
        typeAhead: true,
        //mode: 'local',
        hidden: true,
        displayField: 'name',
	valueField: 'value',
        id: 'stateCodes',
        name: 'stateCodes',
        hiddenName: 'state_hidden_name',
        forceSelection: true,
        triggerAction: 'all',
        emptyText:'Select a state...',
        loadingText:'loading...',
        selectOnFocus:true
    });
    
    this.proxy = new Ext.data.HttpProxy({
          	url: '/djjs_webapp/getStates'
	        });
	     
	     
Ext.getCmp('stateCodes').on("select", function(){
		Ext.getCmp('districtCodes').setValue('Select District...');
			
	Ext.getCmp('districtCodes').store.proxy = new Ext.data.HttpProxy({ url: '/djjs_webapp/getStates?param=getDistricts&param2='+Ext.getCmp('stateCodes').getValue()
					});
		Ext.getCmp('districtCodes').store.load();
		Ext.getCmp('districtCodes').show();
		
	},this);

	     
this.districtStoreProxy = new Ext.data.HttpProxy({
    	url:'/djjs_webapp/getStates?param=getDistricts&param2='
	});	     

this.districtStore = new Ext.data.Store({
		proxy: this.districtStoreProxy,
		reader: new Ext.data.JsonReader({
		root: 'rows',
		totalProperty: 'totalCount',
		id: 'districtcombo',
		fields:[{"name":"value"},{"name":"name"}]
		}),
		
		remoteSort : 1
	});


this.districts = new Ext.form.ComboBox({
        store: this.districtStore,
        typeAhead: true,
        hidden: true,
        displayField: 'name',
	valueField: 'value',
        id: 'districtCodes',
        name: 'districtCodes',
        hiddenName: 'district_hidden_name',
        forceSelection: true,
        triggerAction: 'all',
        emptyText:'Select a district...',
        loadingText:'loading...',
        selectOnFocus:true
    });
	        
  this.selectsex = new Ext.form.RadioGroup({
                        id:'selectsexID',
                        name: 'selectsexID',
                        items:[
                    {boxLabel: 'Male', name: 'sex', inputValue: 1, checked: true},
                    {boxLabel: 'Female', name: 'sex', inputValue: 2}
                    ]
                    });
   this.status_label = new Ext.form.Label({
     			id:'statusLabel',
     	                 html:'  Status ' 
                         });
   
   
   this.swami = new Ext.form.RadioGroup({
                           id:'selectSwami',
                           name: 'selectSwami',
                           items:[
                       {boxLabel: 'Smarpit', name: 'swami', inputValue: 1},
                       {boxLabel: 'Premi', name: 'swami', inputValue: 2, checked: true},
                       {boxLabel: 'Not Deekshit', name: 'swami', inputValue: 3}
                       ]
                    });
  
  this.vip_label = new Ext.form.Label({
  			id:'vipLabel',
  	                 html:'   Category  ' 
                         });
  
  this.vip = new Ext.form.RadioGroup({
                           id:'isvip',
                           name: 'isvip',
                           items:[
                       {boxLabel: 'General', name: 'sam', inputValue: 1},
                       {boxLabel: 'Vip', name: 'sam', inputValue: 2, checked: true},
                       {boxLabel: 'V.Vip', name: 'sam', inputValue: 3}
                       ]
                    });                  



this.phone_mobile_label = new Ext.form.Label({
			id:'phoneMobileLabel',
	                 html:'    Mobile Number  ' 
                         });

this.phone_mobile_text = new Ext.form.TextField({
                          id: 'phoneMobileText',
                          name: 'phoneMobileText'
                          });

this.email_label = new Ext.form.Label({
			id:'emailLabel',
	                 html:' E-Mail  ' 
                         });

this.email_text = new Ext.form.TextField({
                          id: 'emailText',
                          name: 'emailText'
                          });


this.phone_ll_label = new Ext.form.Label({
			id:'phoneLandLabel',
	                 html:'   Phone Number  ' 
                         });

this.phone_ll_text = new Ext.form.TextField({
                          id: 'phoneLandText',
                          name: 'phoneLandText'
                          });


this.related_to_label = new Ext.form.Label({
			id:'relatedToLabel',
	                 html:'Related To Ashram(Enter ashram id) ' 
                         });

this.related_to_text = new Ext.form.TextField({
                          id: 'relatedToText',
                          name: 'relatedToText'
                          });

/**** EDUCATION *****/

                   
                          
this.occupation_label = new Ext.form.Label({
			id:'occupationLabel',
	                 html:'Occupation ' 
                         });

this.occupation_text = new Ext.form.TextField({
                          id: 'occupationText',
                          name: 'occupationText'
                          });
 
 this.other_prof_label = new Ext.form.Label({
 			id:'otherProfLabel',
 	                 html:'Other Professional Info. ' 
                          });
 
 this.other_prof_text = new Ext.form.TextField({
                           id: 'otherProfText',
                           name: 'otherProfText'
                          });
  
  /*** EDUCATION ***/


/**** DEEKSHA INFO. ****/

this.deeksha_aashram_label = new Ext.form.Label({
 			id:'deekshaAshramLabel',
 	                 html:'Deeksha Address(Enter Description) ' 
                          });
 
 this.deeksha_aashram_text = new Ext.form.TextField({
                           id: 'deekshaAshramText',
                           name: 'deekshaAshramText'
                          });
 
 this.deeksha_date_label = new Ext.form.Label({
  			id:'deekshaDateLabel',
  	                 html:'Deeksha Date ' 
                          });
 
this.deeksha_date_value = new Ext.ux.form.XDateField({
		            id :'deekshaDate',
		            name :'deekshaDate'
		        }); 

/**** DEEKSHA INFO. ****/


 this.betweenLabel = new Ext.form.Label({
    			id:'betweenLabel',
    	                 html:'  between  ' 
                         });

this.maxdob = new Ext.ux.form.XDateField({
		            id :'maxdob',
		            name :'maxdob'
		        }); 

this.maxddate = new Ext.ux.form.XDateField({
		            id :'maxddate',
		            name :'maxddate'
		        }); 
 this.betweenLabel2 = new Ext.form.Label({
    			id:'betweenLabel2',
    	                 html:'  between  ' 
                         });
                         
      /*** sewa **/                   
  this.sewaLabel = new Ext.form.Label({
    			id:'sewaLabel',
    	                 html:'Sewa' 
                         });                         

this.sewaGridStore = new Ext.data.Store({
		proxy: new Ext.data.HttpProxy({
		url:'/djjs_webapp/getSewas?method=getAll',
		method: 'GET' 
		}),
		autoLoad: true,
		reader: new Ext.data.JsonReader({
		root: 'rows',
		totalProperty: 'totalCount',
		id: 'sewaStore',
		fields:[{"name":"value"},{"name":"name"}]
		}),
		remoteSort : 1
	});                     

this.sewaGrid = new Ext.grid.GridPanel({
       store: this.sewaGridStore,
        columns: [
            {id:'sewaId',header: "Id", width: 20, hidden: true},
            {id: 'sewaDesc', header: "Sewa", width: 160, sortable: true},
        ],
        stripeRows: true,
        autoExpandColumn: 'sewaDesc',
        height:250,
        width:200
    });


this.sewaSelectionText = new Ext.form.TextField({
                          id: 'sewaSelectionName',
                          name: 'sewaSelectionName',
                          hidden: 'true',
                          value: ""
                          });
                          
 
/** qualification **/

this.qualification_label = new Ext.form.Label({
			id:'qualificationLabel',
	                 html:'Qualification ' 
                         });

this.qaStore = new Ext.data.Store({
		proxy: new Ext.data.HttpProxy({
		url:'/djjs_webapp/getSewas?method=qaJSONList',
		method: 'GET' 
		}),
		autoLoad: true,
		reader: new Ext.data.JsonReader({
		root: 'rows',
		totalProperty: 'totalCount',
		id: 'qaStore',
		fields:[{"name":"value"},{"name":"name"}]
		}),
		remoteSort : 1
	});                     

this.qualificationGrid = new Ext.grid.GridPanel({
       store: this.qaStore,
        columns: [
            {id:'qaId',header: "Id", width: 20, hidden: true},
            {id: 'qaDesc', header: "Qualification", width: 160, sortable: true},
        ],
        stripeRows: true,
        autoExpandColumn: 'qaDesc',
        height:250,
        width:200
    });


this.qualification_text = new Ext.form.TextField({
                          id: 'qualificationText',
                          name: 'qualificationText',
                          hidden: 'true',
                          value: ""
                          });
                          
this.occup_catg_label = new Ext.form.Label({
  			id:'occup_catg_label',
  	                 html:'   Work Category  ' 
                         });
  
  this.occup_catg = new Ext.form.RadioGroup({
                           id:'occup_catg',
                           name: 'occup_catg',
                           width: "400px",
                           items:[
                       {boxLabel: 'Corporate', name: 'sam2', inputValue: 1},
                       {boxLabel: 'Medical', name: 'sam2', inputValue: 2},
                       {boxLabel: 'Business', name: 'sam2', inputValue: 3},
                       {boxLabel: 'Others', name: 'sam2', inputValue: 4, checked: true}
                       ]
                    });

/** qualification **/
}



 