$(document).ready(function () {
	var  overlay = $('.overlay');
	$('[data-toggle="offcanvas"]').click(function () {
	    $('#wrapper').toggleClass('toggled');
	});
	
	$('#form').bootstrapValidator({
        message: 'This value is not valid',
        fields: {
        	email: {
                validators: {
                    emailAddress: {
                        message: 'The input is not a valid email address'
                    }
                }
            },
        	password: {
                validators: {
                    notEmpty: {
                        message: 'The password is required and cannot be empty'
                    },
                    stringLength: {
                        min: 3,
                        max: 10,
                        message: 'The password must be more than 3 and less than 10 characters long'
                    },
                    identical: {
                        field: 'passwrd2',
                        message: 'The password and its confirm are not the same'
                    },
                    different: {
                        field: 'email',
                        message: 'The password cannot be the same as username'
                    }
                }
            },
            password2: {
                validators: {
                    notEmpty: {
                        message: 'The confirm password is required and cannot be empty'
                    },
                    identical: {
                        field: 'password',
                        message: 'The password and its confirm are not the same'
                    },
                    different: {
                        field: 'email',
                        message: 'The password cannot be the same as username'
                    }
                }
            },
            firstName: {
                validators: {
                    notEmpty: {
                        message: 'The first name is required and cannot be empty'
                    }
                }
            },
            lastName: {
                validators: {
                    notEmpty: {
                        message: 'The last name is required and cannot be empty'
                    }
                }
            },
            birthday: {
                validators: {
                    date: {
                        format: 'YYYY/MM/DD',
                        message: 'The birthday is not valid (YYYY/MM/DD)'
                    }
                }
            },
            city: {
                validators: {
                    notEmpty: {
                        message: 'The city is required and cannot be empty'
                    }
                }
            },
            code: {
                validators: {
                    notEmpty: {
                        message: 'The post code is required and cannot be empty'
                    }
                }
            },
            address: {
                validators: {
                    notEmpty: {
                        message: 'The address is required and cannot be empty'
                    }
                }
            },
            number: {
                validators: {
                    notEmpty: {
                        message: 'The phone number is required and cannot be empty'
                    },
                    numeric: {
                        message: 'The phone number is invalued'
                    },
                }
            },
            ch: {
            	validators: {
            		notEmpty: {
            			message: 'Please aggree to the aggreement before you continue'
            				  }
            	}
            }
        }
    });
	
	$('#bform').bootstrapValidator({
        message: 'This value is not valid',
        fields: {
        	account1: {
                validators: {
                    stringLength: {
                        min: 3,
                        max: 3,
                        message: 'The bank transit code must be 3 characters long'
                    },
                    numeric: {
                        message: 'The bank transit code is invalued'
                    },
                    notEmpty: {
                        message: 'The bank transit code is required and cannot be empty'
                    }
                }
            },
            account2: {
                validators: {
                    stringLength: {
                        min: 3,
                        max: 3,
                        message: 'The institution code must be 3 characters long'
                    },
                    numeric: {
                        message: 'The institution code is invalued'
                    },
                    notEmpty: {
                        message: 'The institution code is required and cannot be empty'
                    }
                }
            },
            account3: {
                validators: {
                    stringLength: {
                        min: 8,
                        max: 8,
                        message: 'The account number must be 8 characters long'
                    },
                    numeric: {
                        message: 'The account number is invalued'
                    },
                    notEmpty: {
                        message: 'The account number is required and cannot be empty'
                    }
                }
            },
        	password: {
                validators: {
                    notEmpty: {
                        message: 'The password is required and cannot be empty'
                    },
                    stringLength: {
                        min: 4,
                        max: 6,
                        message: 'The password must be more than 4 but less than 6 characters long'
                    },
                }
            },
            ch: {
            	validators: {
            		notEmpty: {
            			message: 'Please aggree to the aggreement before you continue'
            				  }
            		}
            	}
        	}
    	});

});

