
/** sample dummy data */

create table districts ( name varchar(100), sid int,  id int PRIMARY KEY, FOREIGN KEY (sid) references states(id)
                     )ENGINE=InnoDB DEFAULT CHARSET=latin1; 

/* districts in delhi  */
insert into districts values ('Central Delhi', 1, 41);
insert into districts values ('East Delhi', 1,42);
insert into districts values ('New Delhi', 1, 43);
insert into districts values ('North Delhi', 1, 44);
insert into districts values ('North East Delhi', 1, 45);
insert into districts values ('North West Delhi', 1 , 46);
insert into districts values ('South Delhi', 1, 47);
insert into districts values ('South West Delhi', 1, 48);
insert into districts values ('West Delhi', 1, 49);


/* districts in haryana */
insert into districts values ('Ambala', 2, 50);
insert into districts values ('Bhiwani', 2, 51);
insert into districts values ('Faridabad', 2, 52);
insert into districts values ('Fatehabad', 2, 53);
insert into districts values ('Gurgaon', 2, 54);
insert into districts values ('Hisar', 2, 55);
insert into districts values ('Jhajjar', 2, 56);
insert into districts values ('Jind', 2, 57);
insert into districts values ('Kaithal', 2, 58);
insert into districts values ('Karnal', 2, 59);
insert into districts values ('Kurukshetra', 2, 60);
insert into districts values ('Mahendragarh', 2, 61);
insert into districts values ('Mewat', 2, 62);
insert into districts values ('Panchkula', 2, 63);
insert into districts values ('Panipat', 2, 64);
insert into districts values ('Rewari', 2, 65);
insert into districts values ('Rohtak', 2, 66);
insert into districts values ('Sirsa', 2, 67);
insert into districts values ('Sonipat', 2, 68);
insert into districts values ('Yamunanagar', 2, 69);

/* districts in Andhra Pradesh */
insert into districts values ('Adilabad', 3, 90);
insert into districts values ('Anantapur', 3, 91);
insert into districts values ('Chittoor', 3, 92);
insert into districts values ('Cuddapah', 3, 93);
insert into districts values ('East Godavari', 3, 94);
insert into districts values ('Guntur', 3, 95);
insert into districts values ('Hyderabad', 3, 96);
insert into districts values ('Karimnagar', 3, 97);
insert into districts values ('Khammam', 3, 98);
insert into districts values ('Krishna', 3, 99);
insert into districts values ('Kurnool', 3, 101);
insert into districts values ('Mahbubnagar', 3, 102);
insert into districts values ('Medak', 3, 103);
insert into districts values ('Nalgonda', 3, 104);
insert into districts values ('Nellore', 3, 105);
insert into districts values ('Nizamabad', 3, 106);
insert into districts values ('Prakasam', 3, 107);
insert into districts values ('Rangareddi', 3, 108);
insert into districts values ('Srikakulam', 3, 109);
insert into districts values ('Visakhapatnam', 3, 110);
insert into districts values ('Vizianagaram', 3, 111);
insert into districts values ('Warangal', 3, 112);
insert into districts values ('West Godavari', 3, 113);

/* districts in bihar */
insert into districts values ('Araria', 4, 120);
insert into districts values ('Aurangabad', 4, 121);
insert into districts values ('Banka', 4, 122);
insert into districts values ('Begusarai', 4, 123);
insert into districts values ('Bhagalpur', 4, 124);
insert into districts values ('Bhojpur', 4, 125);
insert into districts values ('Buxar', 4, 126);
insert into districts values ('Darbhanga', 4, 127);
insert into districts values ('Gaya', 4, 128);
insert into districts values ('Gopalganj', 4, 129);
insert into districts values ('Jamui', 4, 130);
insert into districts values ('Jehanabad', 4, 131);
insert into districts values ('Kaimur', 4, 132);
insert into districts values ('Katihar', 4, 133);
insert into districts values ('Khagaria', 4, 134);
insert into districts values ('Kishanganj', 4, 135);
insert into districts values ('Lakhisarai', 4, 136);
insert into districts values ('Madhepura', 4, 137);
insert into districts values ('Madhubani', 4, 138);
insert into districts values ('Munger', 4, 139);
insert into districts values ('Muzaffarpur', 4, 140);
insert into districts values ('Nalanda', 4, 141);
insert into districts values ('Nawada', 4, 142);
insert into districts values ('Pashchim Champaran', 4, 143);
insert into districts values ('Patna', 4, 144);
insert into districts values ('Purba Champaran', 4, 145);
insert into districts values ('Purnia', 4, 146);
insert into districts values ('Rohtas', 4, 147);
insert into districts values ('Saharsa', 4, 148);
insert into districts values ('Samastipur', 4, 149);
insert into districts values ('Saran', 4, 150);
insert into districts values ('Sheikhpura', 4, 151);
insert into districts values ('Sheohar', 4, 152);
insert into districts values ('Sitamarhi', 4, 153);
insert into districts values ('Siwan', 4, 154);
insert into districts values ('Supaul', 4, 155);
insert into districts values ('Vaishali', 4, 156);

/* districts in punjab */
insert into districts values ('Amritsar', 5, 70);
insert into districts values ('Bathinda', 5, 71);
insert into districts values ('Faridkot', 5, 72);
insert into districts values ('Fatehgarh', 5, 73);
insert into districts values ('Ferozepur', 5, 74);
insert into districts values ('Gurdaspur', 5, 75);
insert into districts values ('Hoshiarpur', 5, 76);
insert into districts values ('Jalandhar', 5, 77);
insert into districts values ('Kapurthala', 5, 78);
insert into districts values ('Ludhiana', 5, 79);
insert into districts values ('Mansa', 5, 80);
insert into districts values ('Moga', 5, 81);
insert into districts values ('Muktsar', 5, 82);
insert into districts values ('Nawanshahr', 5, 83);
insert into districts values ('Patiala', 5, 84);
insert into districts values ('Rupnagar', 5, 85);
insert into districts values ('Sangrur', 5, 86);
insert into districts values ('Barnala', 5, 87);
insert into districts values ('Mohali', 5, 88);
insert into districts values ('Tarn Taran', 5, 89);

/* districts in chattisgarh */
insert into districts values ('Bastar', 6, 160);
insert into districts values ('Bilaspur', 6, 161);
insert into districts values ('Dantewada', 6, 162);
insert into districts values ('Dhamtari', 6, 163);
insert into districts values ('Durg', 6, 164);
insert into districts values ('Janjgir-Champa', 6, 165);
insert into districts values ('Jashpur', 6, 166);
insert into districts values ('Kanker', 6, 167);
insert into districts values ('Kawardha', 6, 168);
insert into districts values ('Korba', 6, 169);
insert into districts values ('Koriya', 6, 170);
insert into districts values ('Mahasamund', 6, 171);
insert into districts values ('Raigarh', 6, 172);
insert into districts values ('Raipur', 6, 173);
insert into districts values ('Rajnandgaon', 6, 174);
insert into districts values ('Surguja', 6, 175);

/* districts in himachal pradesh */
insert into districts values ('Bilaspur', 7, 180);
insert into districts values ('Chamba', 7, 181);
insert into districts values ('Hamirpur', 7, 182);
insert into districts values ('Kangra', 7, 183);
insert into districts values ('Kinnaur', 7, 184);
insert into districts values ('Kullu', 7, 185);
insert into districts values ('Lahaul and Spiti', 7, 186);
insert into districts values ('Mandi', 7, 187);
insert into districts values ('Shimla', 7, 188);
insert into districts values ('Sirmaur', 7, 189);
insert into districts values ('Solan', 7, 190);
insert into districts values ('Una', 7, 191);

/* districts in madhya pradesh */
insert into districts values ('Balaghat', 8, 201);
insert into districts values ('Barwani', 8, 202);
insert into districts values ('Betul', 8, 203);
insert into districts values ('Bhind', 8, 204);
insert into districts values ('Bhopal', 8, 205);
insert into districts values ('Chhatarpur', 8, 206);
insert into districts values ('Chhindwara', 8, 207);
insert into districts values ('Damoh', 8, 208);
insert into districts values ('Datia', 8, 209);
insert into districts values ('Dewas', 8, 210);
insert into districts values ('Dhar', 8, 211);
insert into districts values ('Dindori', 8, 212);
insert into districts values ('Khandwa (East Nimar)', 8, 213);
insert into districts values ('Guna', 8, 214);
insert into districts values ('Gwalior', 8, 215);
insert into districts values ('Harda', 8, 216);
insert into districts values ('Hoshangabad', 8, 217);
insert into districts values ('Indore', 8, 218);
insert into districts values ('Jabalpur', 8, 219);
insert into districts values ('Jhabua', 8, 220);
insert into districts values ('Katni', 8, 221);
insert into districts values ('Mandla', 8, 222);
insert into districts values ('Mandsaur', 8, 223);
insert into districts values ('Morena', 8, 224);
insert into districts values ('Narsinghpur', 8, 225);
insert into districts values ('Neemuch', 8, 226);
insert into districts values ('Panna', 8, 227);
insert into districts values ('Raisen', 8, 228);
insert into districts values ('Rajgarh', 8, 229);
insert into districts values ('Ratlam', 8, 230);
insert into districts values ('Rewa', 8, 231);
insert into districts values ('Sagar', 8, 232);
insert into districts values ('Satna', 8, 233);
insert into districts values ('Sehore', 8, 234);
insert into districts values ('Seoni', 8, 235);
insert into districts values ('Shahdol', 8, 236);
insert into districts values ('Shajapur', 8, 237);
insert into districts values ('Sheopur', 8, 238);
insert into districts values ('Shivpuri', 8, 239);
insert into districts values ('Sidhi', 8, 240);
insert into districts values ('Tikamgarh', 8, 241);
insert into districts values ('Ujjain', 8, 242);
insert into districts values ('Umaria', 8, 243);
insert into districts values ('Vidisha', 8, 244);
insert into districts values ('Khargone', 8, 245);
insert into districts values ('Anuppur', 8, 246);
insert into districts values ('Burhanpur', 8, 247);
insert into districts values ('Ashoknagar', 8, 248);
insert into districts values ('Singrauli', 8, 249);


/* districts in J & K*/
insert into districts values ('Anantnag', 9, 250);
insert into districts values ('Budgam', 9, 251);
insert into districts values ('Baramulla', 9, 252);
insert into districts values ('Doda', 9, 253);
insert into districts values ('Jammu', 9, 254);
insert into districts values ('Kargil', 9, 255);
insert into districts values ('Kathua', 9, 256);
insert into districts values ('Kupwara', 9, 257);
insert into districts values ('Leh', 9, 258);
insert into districts values ('Pulwama', 9, 259);
insert into districts values ('Poonch', 9, 260);
insert into districts values ('Rajauri', 9, 261);
insert into districts values ('Srinagar', 9, 262);
insert into districts values ('Udhampur', 9, 263);
insert into districts values ('Samba', 9, 264);


/* districts in karnataka */
insert into districts values ('Bagalkot', 10, 270);
insert into districts values ('Bangalore', 10, 271);
insert into districts values ('Bangalore Rural', 10, 272);
insert into districts values ('Belgaum', 10, 273);
insert into districts values ('Bellary', 10, 274);
insert into districts values ('Bidar', 10, 275);
insert into districts values ('Bijapur', 10, 276);
insert into districts values ('Chamrajnagar', 10, 277);
insert into districts values ('Chickmagalur', 10, 278);
insert into districts values ('Chitradurga', 10, 279);
insert into districts values ('Dakshin Kannada', 10, 280);
insert into districts values ('Davangere', 10, 281);
insert into districts values ('Dharwad', 10, 282);
insert into districts values ('Gadag', 10, 283);
insert into districts values ('Gulbarga', 10, 284);
insert into districts values ('Hassan', 10, 285);
insert into districts values ('Haveri', 10, 286);
insert into districts values ('Kodagu', 10, 287);
insert into districts values ('Kolar', 10, 288);
insert into districts values ('Koppal', 10, 289);
insert into districts values ('Mandya', 10, 290);
insert into districts values ('Mysore', 10, 291);
insert into districts values ('Raichur', 10, 292);
insert into districts values ('Shimoga', 10, 293);
insert into districts values ('Tumkur', 10, 294);
insert into districts values ('Udupi', 10, 295);
insert into districts values ('Uttar Kannada', 10, 296);


/* districts in maharashtra */
insert into districts values ('Ahmednagar', 11, 300);
insert into districts values ('Akola', 11, 301);
insert into districts values ('Amravati', 11, 302);
insert into districts values ('Aurangabad', 11, 303);
insert into districts values ('Bhandara', 11, 304);
insert into districts values ('Beed', 11, 305);
insert into districts values ('Buldhana', 11, 306);
insert into districts values ('Chandrapur', 11, 307);
insert into districts values ('Dhule', 11, 308);
insert into districts values ('Gadchiroli', 11, 309);
insert into districts values ('Gondia', 11, 310);
insert into districts values ('Hingoli', 11, 311);
insert into districts values ('Jalgaon', 11, 312);
insert into districts values ('Jalna', 11, 313);
insert into districts values ('Kolhapur', 11, 314);
insert into districts values ('Latur', 11, 315);
insert into districts values ('Mumbai', 11, 316);
insert into districts values ('Mumbai Suburban', 11, 317);
insert into districts values ('Nagpur', 11, 318);
insert into districts values ('Nanded', 11, 319);
insert into districts values ('Nandurbar', 11, 320);
insert into districts values ('Nashik', 11, 321);
insert into districts values ('Osmanabad', 11, 322);
insert into districts values ('Parbhani', 11, 323);
insert into districts values ('Pune', 11, 324);
insert into districts values ('Raigad', 11, 325);
insert into districts values ('Ratnagiri', 11, 326);
insert into districts values ('Sangli', 11, 327);
insert into districts values ('Satara', 11, 328);
insert into districts values ('Sindhudurg', 11, 329);
insert into districts values ('Solapur', 11, 330);
insert into districts values ('Thane', 11, 331);
insert into districts values ('Wardha', 11, 332);
insert into districts values ('Washim', 11, 333);
insert into districts values ('Yavatmal', 11, 334);


/* districts in orissa */
insert into districts values ('Anugul', 12, 340);
insert into districts values ('Balangir', 12, 341);
insert into districts values ('Baleswar', 12, 342);
insert into districts values ('Bargarh', 12, 343);
insert into districts values ('Boudh', 12, 344);
insert into districts values ('Bhadrak', 12, 345);
insert into districts values ('Cuttack', 12, 346);
insert into districts values ('Deogarh', 12, 347);
insert into districts values ('Dhenkanal', 12, 348);
insert into districts values ('Gajapati', 12, 349);
insert into districts values ('Ganjam', 12, 350);
insert into districts values ('Jagatsinghapur', 12, 351);
insert into districts values ('Jajpur', 12, 352);
insert into districts values ('Jharsuguda', 12, 353);
insert into districts values ('Kalahandi', 12, 354);
insert into districts values ('Kandhamal', 12, 355);
insert into districts values ('Kendrapara', 12, 356);
insert into districts values ('Kendujhar', 12, 357);
insert into districts values ('Khordha', 12, 358);
insert into districts values ('Koraput', 12, 359);
insert into districts values ('Malkangiri', 12, 360);
insert into districts values ('Mayurbhanj', 12, 361);
insert into districts values ('Nabarangapur', 12, 362);
insert into districts values ('Nayagarh', 12, 363);
insert into districts values ('Nuapada', 12, 364);
insert into districts values ('Puri', 12, 365);
insert into districts values ('Rayagada', 12, 366);
insert into districts values ('Sambalpur', 12, 367);
insert into districts values ('Subarnapur', 12, 368);
insert into districts values ('Sundergarh', 12, 369);


/* districts in rajasthan */
insert into districts values ('Ajmer', 13, 370);
insert into districts values ('Alwar', 13, 371);
insert into districts values ('Banswara', 13, 372);
insert into districts values ('Baran', 13, 373);
insert into districts values ('Barmer', 13, 374);
insert into districts values ('Bharatpur', 13, 375);
insert into districts values ('Bhilwara', 13, 376);
insert into districts values ('Bikaner', 13, 377);
insert into districts values ('Bundi', 13, 378);
insert into districts values ('Chittorgarh', 13, 379);
insert into districts values ('Churu', 13, 380);
insert into districts values ('Dausa', 13, 381);
insert into districts values ('Dholpur', 13, 382);
insert into districts values ('Dungarpur', 13, 383);
insert into districts values ('Sri Ganganagar', 13, 384);
insert into districts values ('Jaipur', 13, 385);
insert into districts values ('Jaisalmer', 13, 386);
insert into districts values ('Jalor', 13, 387);
insert into districts values ('Jhalawar', 13, 388);
insert into districts values ('Jhunjhunu', 13, 389);
insert into districts values ('Jodhpur', 13, 390);
insert into districts values ('Karauli', 13, 391);
insert into districts values ('Kota', 13, 392);
insert into districts values ('Nagaur', 13, 393);
insert into districts values ('Pali', 13, 394);
insert into districts values ('Rajsamand', 13, 395);
insert into districts values ('Sawai Madhopur', 13, 396);
insert into districts values ('Sikar', 13, 397);
insert into districts values ('Sirohi', 13, 398);
insert into districts values ('Tonk', 13, 399);
insert into districts values ('Udaipur', 13, 400);
insert into districts values ('Pratapgarh', 13, 401);


/* districts in UP */
insert into districts values ('Agra', 14, 410);
insert into districts values ('Aligarh', 14, 411);
insert into districts values ('Allahabad', 14, 412);
insert into districts values ('Ambedkar Nagar', 14, 413);
insert into districts values ('Auraiya', 14, 414);
insert into districts values ('Azamgarh', 14, 415);
insert into districts values ('Bagpat', 14, 416);
insert into districts values ('Bahraich', 14, 417);
insert into districts values ('Ballia', 14, 418);
insert into districts values ('Balrampur', 14, 419);
insert into districts values ('Banda', 14, 420);
insert into districts values ('Barabanki', 14, 421);
insert into districts values ('Bareilly', 14, 422);
insert into districts values ('Basti', 14, 423);
insert into districts values ('Bijnor', 14, 424);
insert into districts values ('Budaun', 14, 425);
insert into districts values ('Bulandshahr', 14, 426);
insert into districts values ('Chandauli', 14, 427);
insert into districts values ('Chitrakoot', 14, 428);
insert into districts values ('Deoria', 14, 429);
insert into districts values ('Etah', 14, 430);
insert into districts values ('Etawah', 14, 431);
insert into districts values ('Faizabad', 14, 432);
insert into districts values ('Farrukhabad', 14, 433);
insert into districts values ('Fatehpur', 14, 434);
insert into districts values ('Firozabad', 14, 435);
insert into districts values ('Gautam Buddha Nagar', 14, 436);
insert into districts values ('Ghazipur', 14, 437);
insert into districts values ('Ghaziabad', 14, 438);
insert into districts values ('Gonda', 14, 439);
insert into districts values ('Gorakhpur', 14, 440);
insert into districts values ('Hamirpur', 14, 441);
insert into districts values ('Hardoi', 14, 442);
insert into districts values ('Hathras', 14, 443);
insert into districts values ('Jalaun', 14, 444);
insert into districts values ('Jaunpur', 14, 445);
insert into districts values ('Jhansi', 14, 446);
insert into districts values ('Jyotiba Phule Nagar', 14, 447);
insert into districts values ('Kannauj', 14, 448);
insert into districts values ('Kanpur Dehat', 14, 449);
insert into districts values ('Kanpur Nagar', 14, 450);
insert into districts values ('Kaushambi', 14, 451);
insert into districts values ('Kheri', 14, 452);
insert into districts values ('Kushinagar', 14, 453);
insert into districts values ('Lalitpur', 14, 454);
insert into districts values ('Lucknow', 14, 455);
insert into districts values ('Mahoba', 14, 456);
insert into districts values ('Maharajganj', 14, 457);
insert into districts values ('Mainpuri', 14, 458);
insert into districts values ('Mathura', 14, 459);
insert into districts values ('Mau', 14, 460);
insert into districts values ('Meerut', 14, 461);
insert into districts values ('Mirzapur', 14, 462);
insert into districts values ('Moradabad', 14, 463);
insert into districts values ('Muzaffarnagar', 14, 464);
insert into districts values ('Pilibhit', 14, 465);
insert into districts values ('Pratapgarh', 14, 466);
insert into districts values ('RaeBareli', 14, 467);
insert into districts values ('Rampur', 14, 468);
insert into districts values ('Saharanpur', 14, 469);
insert into districts values ('Sant Kabir Nagar', 14, 470);
insert into districts values ('Sant Ravidas Nagar', 14, 471);
insert into districts values ('Shahjahanpur', 14, 472);
insert into districts values ('Shravasti', 14, 473);
insert into districts values ('Siddharthnagar', 14, 474);
insert into districts values ('Sitapur', 14, 475);
insert into districts values ('Sonbhadra', 14, 476);
insert into districts values ('Sultanpur', 14, 477);
insert into districts values ('Unnao', 14, 478);
insert into districts values ('Varanasi', 14, 479);


/* districts in uttrakhand */
insert into districts values ('Almora', 15, 480);
insert into districts values ('Bageshwar', 15, 481);
insert into districts values ('Chamoli', 15, 482);
insert into districts values ('Champawat', 15, 483);
insert into districts values ('Dehradun', 15, 484);
insert into districts values ('Pauri Garhwal', 15, 485);
insert into districts values ('Haridwar', 15, 486);
insert into districts values ('Nainital', 15, 487);
insert into districts values ('Pithoragarh', 15, 488);
insert into districts values ('Rudraprayag', 15, 489);
insert into districts values ('Tehri Garhwal', 15, 490);
insert into districts values ('Udham Singh Nagar', 15, 491);
insert into districts values ('Uttarkashi', 15, 492);

/* district in chandigarh */
insert into districts values ('Chandigarh', 33, 499);


/* districts in andamaan */
insert into districts values ('Nicobar', 35, 510);
insert into districts values ('North and Middle Andaman', 35, 511);
insert into districts values ('South Andaman', 35, 512);

/* districts in arunachal pradesh */
insert into districts values ('Anjaw', 36, 520);
insert into districts values ('Changlang', 36, 521);
insert into districts values ('Dibang Valley', 36, 522);
insert into districts values ('East Siang', 36, 523);
insert into districts values ('Kurung Kumey', 36, 524);
insert into districts values ('East Kameng', 36, 525);
insert into districts values ('Lohit', 36, 526);
insert into districts values ('Lower Dibang Valley', 36, 527);
insert into districts values ('Lower Subansiri', 36, 528);
insert into districts values ('Papum Pare', 36, 529);
insert into districts values ('Tirap', 36, 530);
insert into districts values ('Tawang', 36, 531);
insert into districts values ('Upper Siang', 36, 532);
insert into districts values ('West Kameng', 36, 533);
insert into districts values ('Upper Subansiri', 36, 534);
insert into districts values ('West Siang', 36, 535);

/* districts in assam */
insert into districts values ('Baksa', 37, 540);
insert into districts values ('Barpeta', 37, 541);
insert into districts values ('Bongaigaon', 37, 548);
insert into districts values ('Cachar', 37, 542);
insert into districts values ('Chirang', 37, 543);
insert into districts values ('Darrang', 37, 544);
insert into districts values ('Dhemaji', 37, 545);
insert into districts values ('Dhubri', 37, 546);
insert into districts values ('Dibrugarh', 37, 547);
insert into districts values ('Goalpara', 37, 550);
insert into districts values ('North Cachar Hills', 37, 555);
insert into districts values ('Sivasagar', 37, 556);
insert into districts values ('Nalbari', 37, 557);
insert into districts values ('Udalguri', 37, 558);
insert into districts values ('Tinsukia', 37, 559);
insert into districts values ('Sonitpur', 37, 560);
insert into districts values ('Nagaon', 37, 561);
insert into districts values ('Morigaon', 37, 562);
insert into districts values ('Lakhimpur', 37, 563);
insert into districts values ('Kokrajhar', 37, 564);
insert into districts values ('Karimganj', 37, 565);
insert into districts values ('Karbi Anglong', 37, 566);
insert into districts values ('Kamrup Metropolitan', 37, 567);
insert into districts values ('Kamrup', 37, 568);
insert into districts values ('Jorhat', 37, 569);
insert into districts values ('Hailakandi', 37, 551);
insert into districts values ('Golaghat', 37, 552);
	
/* districts in Dadra & Nagar Haveli */
insert into districts values ('Dadra & Nagar Haveli', 38, 570);

/* districts in Daman and Diu */

insert into districts values ('Daman', 39, 580);		
insert into districts values ('Diu', 39, 581);	    	

/* districts in goa */
insert into districts values ('North Goa', 40, 590);
insert into districts values ('South Goa', 40, 591);

/* districts in jharkhand */
insert into districts values ('North', 41, 600);
insert into districts values ('Dhanbad', 41, 601);
insert into districts values ('Dumka', 41, 602);
insert into districts values ('East Singhbhum', 41, 603);
insert into districts values ('Gumla', 41, 604);
insert into districts values ('Garhwa', 41, 605);
insert into districts values ('Godda', 41, 606);
insert into districts values ('Giridih', 41, 607);
insert into districts values ('Hazaribag', 41, 608);
insert into districts values ('Jamtara', 41, 609);
insert into districts values ('Latehar', 41, 610);
insert into districts values ('Khunti', 41, 611);
insert into districts values ('Koderma', 41, 612);
insert into districts values ('Pakur', 41, 613);
insert into districts values ('Palamu', 41, 614);
insert into districts values ('Lohardaga', 41, 615);
insert into districts values ('Ramgarh', 41, 616);
insert into districts values ('Ranchi', 41, 617);
insert into districts values ('Sahibganj', 41, 618);
insert into districts values ('West Singhbhum', 41, 619);
insert into districts values ('Simdega', 41, 620);
insert into districts values ('Seraikela-Kharsawan', 41, 621);
insert into districts values ('Deoghar', 41, 622);
insert into districts values ('Chatra', 41, 623);
insert into districts values ('Bokaro', 41, 624);
	 	   
		
		   
		/*districts in kerala */
insert into districts values ('Alappuzha',42, 700);
insert into districts values ('Ernakulam',42, 701);
insert into districts values ('Ernakulam',42, 702);
insert into districts values ('Kannur',42, 703);
insert into districts values ('Kasargod',42, 704);
insert into districts values ('Kollam',42, 705);
insert into districts values ('Kottayam',42, 706);
insert into districts values ('Kozhikode',42, 707);
insert into districts values ('Malappuram',42, 708);
insert into districts values ('Palakkad',42, 709);
insert into districts values ('Pathanamthitta',42, 710);
insert into districts values ('Thiruvananthapuram',42, 711);
insert into districts values ('Thrissur',42, 712);
insert into districts values ('Wayanad',42, 713);

/* districts in Lakshadweep*/
insert into districts values ('Lakshadweep',43, 720);

/* districts in manipur */
insert into districts values ('Bishnupur',44, 730);
insert into districts values ('Chandel',44, 731);
insert into districts values ('Churachandpur',44, 732);
insert into districts values ('Imphal East',44, 733);
insert into districts values ('Imphal West',44, 734);
insert into districts values ('Senapati',44, 735);
insert into districts values ('Tamenglong',44, 736);
insert into districts values ('Thoubal',44, 737);
insert into districts values ('Ukhrul',44, 738);

	
/* districts in  meghalaya */
insert into districts values ('East Garo Hills',45, 740);
insert into districts values ('Ri Bhoi',45, 741);
insert into districts values ('West Khasi Hills',45, 742);
insert into districts values ('East Khasi Hills',45, 743);
insert into districts values ('South Garo Hills',45, 744);
insert into districts values ('Jaintia Hills',45, 745);
insert into districts values ('West Garo Hills',45, 746);


/* districts in mizoram */
insert into districts values ('Aizawl',46, 750);
insert into districts values ('Champhai',46, 751);
insert into districts values ('Kolasib',46, 752);
insert into districts values ('Lawngtlai',46, 753);
insert into districts values ('Lunglei',46, 754);
insert into districts values ('Mamit',46, 755);
insert into districts values ('Serchhip',46, 756);
insert into districts values ('Saiha',46, 757);

/* districts in nagaland */
insert into districts values ('Dimapur',47, 760);
insert into districts values ('Kephrie',47, 761);
insert into districts values ('Kohima',47, 762);
insert into districts values ('Longleng',47, 763);
insert into districts values ('Mokokchung',47, 764);
insert into districts values ('Mon',47, 765);
insert into districts values ('Peren',47, 766);
insert into districts values ('Phek',47, 767);
insert into districts values ('Tuensang',47, 768);
insert into districts values ('Zunheboto',47, 769);
insert into districts values ('Wokha',47, 770);


/* districts in Pondicherry */
insert into districts values ('Karaikal',48, 781);
insert into districts values ('Mahe',48, 782);
insert into districts values ('Pondicherry',48, 783);
insert into districts values ('Yanam',48, 784);

/* districts in sikkim */
insert into districts values ('East Sikkim',49, 790);
insert into districts values ('North Sikkim',49, 791);
insert into districts values ('South Sikkim',49, 792);
insert into districts values ('West Sikkim',49, 793);
	
/* districts in tamilnadu */
		
		
insert into districts values ('Pudukkottai',50, 800);
insert into districts values ('Ramanathapuram',50, 801);
insert into districts values ('Salem',50, 802);
insert into districts values ('Sivaganga',50, 803);
insert into districts values ('The Nilgiris',50, 804);
insert into districts values ('Perambalur',50, 805);
insert into districts values ('Namakkal',50, 806);
insert into districts values ('Nagapattinam',50, 807);
insert into districts values ('Madurai',50, 808);
insert into districts values ('Krishnagiri',50, 809);
insert into districts values ('Thanjavur',50, 810);
insert into districts values ('Theni',50, 811);
insert into districts values ('Thoothukudi',50, 812);
insert into districts values ('Tiruchirappalli',50, 813);
insert into districts values ('Tirunelveli',50, 814);
insert into districts values ('Tiruvallur',50, 815);
insert into districts values ('Tiruvannamalai',50, 816);
insert into districts values ('Tiruvarur',50, 817);
insert into districts values ('Vellore',50, 818);
insert into districts values ('Viluppuram',50, 819);
insert into districts values ('Virudhunagar',50, 820);
insert into districts values ('Karur',50, 821);
insert into districts values ('Kanyakumari',50, 822);
insert into districts values ('Kanchipuram',50, 823);
insert into districts values ('Erode',50, 824);
insert into districts values ('Dindigul',50, 825);
insert into districts values ('Dharmapuri',50, 826);
insert into districts values ('Cuddalore',50, 827);
insert into districts values ('Coimbatore',50, 828);
insert into districts values ('Chennai',50, 829);
insert into districts values ('Ariyalur',50, 830);		
		
/* districts in tripura */
insert into districts values ('Dhalai',51, 840);
insert into districts values ('West Tripura',51, 841);
insert into districts values ('South Tripura',51, 842);
insert into districts values ('North Tripura',51, 843);

/* districts in west bengal */	 	 	 

insert into districts values ('Birbhum',52, 850);
insert into districts values ('Bardhaman',52, 851);
insert into districts values ('Bankura',52, 852);
insert into districts values ('East Medinipur',52, 861);
insert into districts values ('Darjeeling',52, 862);
insert into districts values ('Cooch Behar',52, 863);
insert into districts values ('Hooghly',52, 864);
insert into districts values ('Howrah',52, 865);
insert into districts values ('Jalpaiguri',52, 866);
insert into districts values ('Malda',52, 867);
insert into districts values ('Murshidabad',52, 868);
insert into districts values ('Nadia',52, 869);
insert into districts values ('North 24 Parganas',52, 860);
insert into districts values ('North Dinajpur',52, 871);
insert into districts values ('Purulia',52, 872);
insert into districts values ('West Medinipur',52, 873);
insert into districts values ('South Dinajpur',52, 874);
insert into districts values ('South 24 Parganas',52, 875);


	
	    	   
	
		
  		
 		

