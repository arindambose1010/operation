<html>

<head>
	<style type="text/css">
		.modal-open {
			overflow: hidden
		}
		.modal {
			position: fixed;
			top: 0;
			right: 0;
			bottom: 0;
			left: 0;
			z-index: 1050;
			display: none;
			overflow: hidden;
			-webkit-overflow-scrolling: touch;
			outline: 0
		}
		.modal.fade .modal-dialog {
			-webkit-transition: -webkit-transform .3s ease-out;
			-o-transition: -o-transform .3s ease-out;
			transition: transform .3s ease-out;
			-webkit-transform: translate3d(0, -25%, 0);
			-o-transform: translate3d(0, -25%, 0);
			transform: translate3d(0, -25%, 0)
		}
		.modal.in .modal-dialog {
			-webkit-transform: translate3d(0, 0, 0);
			-o-transform: translate3d(0, 0, 0);
			transform: translate3d(0, 0, 0)
		}
		.modal-open .modal {
			overflow-x: hidden;
			overflow-y: auto
		}
		.modal-dialog {
			position: relative;
			width: auto;
			margin: 10px
		}
		.modal-content {
			position: relative;
			background-color: #fff;
			-webkit-background-clip: padding-box;
			background-clip: padding-box;
			border: 1px solid #999;
			border: 1px solid rgba(0, 0, 0, .2);
			border-radius: 6px;
			outline: 0;
			-webkit-box-shadow: 0 3px 9px rgba(0, 0, 0, .5);
			box-shadow: 0 3px 9px rgba(0, 0, 0, .5)
		}
		.modal-backdrop {
			position: fixed;
			top: 0;
			right: 0;
			bottom: 0;
			left: 0;
			z-index: 1040;
			background-color: #000
		}
		.modal-backdrop.fade {
			filter: alpha(opacity=0);
			opacity: 0
		}
		.modal-backdrop.in {
			filter: alpha(opacity=50);
			opacity: .5
		}
		.modal-header {
			background: #63B0C7;
			color: #fff;
			min-height: 16.43px;
			padding: 5px 15px;
			border-bottom: 1px solid #e5e5e5
		}
		.modal-header .close {
			margin-top: -2px
		}
		.modal-title {
			margin: 0;
			line-height: 1.42857143
		}
		.modal-body {
			position: relative;
			padding: 5px
		}
		.modal-footer {
			padding: 5px 15px;
			text-align: right;
			border-top: 1px solid #e5e5e5
		}
		.modal-footer .btn+.btn {
			margin-bottom: 0;
			margin-left: 5px
		}
		.modal-footer .btn-group .btn+.btn {
			margin-left: -1px
		}
		.modal-footer .btn-block+.btn-block {
			margin-left: 0
		}
		.modal-scrollbar-measure {
			position: absolute;
			top: -9999px;
			width: 50px;
			height: 50px;
			overflow: scroll
		}
		@media (min-width :768px) {
			.modal-dialog {
				width: 600px;
				margin: 30px auto;
				height: 435px;
			}
			.modal-content {
				-webkit-box-shadow: 0 5px 15px rgba(0, 0, 0, .5);
				box-shadow: 0 5px 15px rgba(0, 0, 0, .5)
			}
			.modal-sm {
				width: 300px
			}
		}
		@media (min-width :992px) {
			.modal-lg {
				width: 900px
			}
		}
		.consent-section {
			margin-bottom: 20px;
		}
		.consent-text {
			display: flex;
			align-items: flex-start;
			margin-bottom: 15px;
		}
		.consent-text input {
			margin-right: 10px;
			margin-top: 2px;
		}
		.consent-text div {
			line-height: 1.5;
		}
		.consent-checkbox {
			display: flex;
			align-items: center;
			margin-bottom: 20px;
		}
		.consent-checkbox input {
			margin-right: 10px;
		}
		#aadhaarDiv {
			margin-top: 20px;
		}
		.hidden {
			display: none;
		}
	.load {
	display: none;
}
.loading {
	display: block;
	position: fixed;
	z-index: 1000000;
	top: 0;
	left: 0;
	height: 100%;
	width: 100%;
	background-position: center;
	background: url('images/Progress.gif') 50% 50% no-repeat;
}
#loadbg {
	content: '';
	position: absolute;
	width: 100%;
	margin: auto;
	left: 0;
	right: 0;
	height: 100%;
	background: #33333382;
	-ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=50)";
	filter: alpha(opacity = 50);
	-moz-opacity: 0.5;
	-khtml-opacity: 0.5;
	opacity: 0.5;
}
	</style>
</head>
<body>
<div class="load" id="loadid"></div>
	<div class="modal fade" id="viewekycModal" style="width: 1285px;">
		<div class="modal-dialog modal-lg" style="width: 1235px;">
			<div class="modal-content">
				<div class="modal-header">
					<div style="display: flex; justify-content: space-between; align-items:center">
						<div>
							<h2 class="modal-title">Consent Form</h2>
						</div>
						<div>
							<button type="button" class="close" data-dismiss="modal"
								style="border: none;background: none;cursor: pointer;" title="close" onclick="closeConsent();">
								<span aria-hidden="true">X</span>
							</button>
						</div>
					</div>
				</div>
				<div class="modal-body">
				<form id="uidauth_model_form" method="post">
					<div class="container">
						<!-- Consent Section -->
						<div id="consentDiv">
							<!-- Patient Consent Declaration -->
							<div class="consent-section" style="margin: 20px;">
								<h3 style="color: red;">Patient Consent Declaration</h3>
								<div class="consent-text" style="margin: 10px 0px 0px 25px">
									<div style="line-height: 1.8;font-size: 15px;">
										I, hereby declare and provide my informed consent as follows:
										<ol>
											<li>I am voluntarily sharing my Aadhaar Number / Virtual
												ID and demographic information issued by the Unique
												Identification Authority of India ("UIDAI"), with "CHFW TG /
												GoTG ("Government of Telangana") for the purpose of creating
												an Ayushman Bharat Health Account number ("ABHA number") and
												Ayushman Bharat Health Account address ("ABHA Address").</li>
											<li>I authorize the collection, sharing, and storage of
												my biometric data, specifically fingerprint, facial
												recognition, iris scan and Aadhaar data with CHFWTG / GoTG.
												This data will be used for the purposes of providing
												healthcare services and validating my identity during my
												current and future hospital encounters.</li>
											<li>I understand that my ABHA number and Address can be
												used and shared for the purpose of providing healthcare
												services or as may be notified by CHFWTG / GoTG from time to
												time.</li>
											<li>I authorize CHFWTG / GoTG to use my Aadhaar number /
												Virtual ID for performing Aadhaar-based authentication with
												UIDAI as per the provisions of the Aadhaar (Targeted
												Delivery of Financial and other Subsidies, Benefits and
												Services) Act, 2016 for the aforesaid purpose.</li>
											<li>I understand that UIDAI will share my e-KYC details,
												or response of "Yes" with CHFWTG / GOTG upon successful
												authentication.</li>
											<li>I have been duly informed about the option of using
												other IDs apart from Aadhaar; however, I consciously choose
												to use Aadhaar number for the purpose of availing benefits
												across the CHFWTG / GoTG.</li>
											<li>I consent to usage of my ABHA address and ABHA
												number for linking of my legacy (past) government health
												records and those which will be generated during this
												encounter or future encounters.</li>
											<li>I authorize the sharing of all my health records
												with healthcare provider(s) for the purpose of providing
												healthcare services to me during this encounter or future
												encounters.</li>
											<li>I consent to the anonymization and subsequent use of
												my government health records for public health purposes.</li>
											<li>I reserve the right to revoke the given consent at
												any point in time as per provisions of Aadhaar Act and
												Regulations.</li>
										</ol>
									</div>
								</div>
								<div class="consent-checkbox" style="margin-top: 12px;">
									<input type="checkbox" id="patientConsentAcknowledge" onclick="checkConsent()">
									<div style="font-size: 15px;">By providing my consent, I acknowledge that I have read, understood, and agree to all the above points.</div>
								</div>
							</div>
							<!-- Data Entry Operator Consent -->
							<div class="consent-section" style="margin-left: 12px;">
								<h3>Data Entry Operator Consent</h3>
								<div class="consent-text">
									<div style="margin-left: 12px;font-size: 15px;}">
										I, <span id="userNamePlaceholder"></span> confirm that I have
										duly informed and explained the beneficiary of the contents of
										consent for the aforementioned purposes.</div>
								</div>
								<div class="consent-checkbox">
									<input type="checkbox" id="operatorConsentAcknowledge" onclick="checkConsent()">
									<div style="font-size: 15px;">
										<b>Note:</b> By sharing my Aadhaar OTP/biometric I am solely
										agreeing on the consent explained to me in the previous screen
										and completing eKYC.
									</div>
								</div>
							</div>
						</div>						
							<div id="aadhaarDiv" style="display: none;">
								<input type="text" id="aadhaarNumber" name="aadhaarNumber"
									placeholder="Enter Aadhaar Number (12 Digits)" maxlength="12" pattern="\d{12}"
									title="Please enter a valid 12-digit Aadhaar number." required
									style="width: 46.70%;margin:15px;padding:12px"
									oninput="validateNumber(this,'aadhaarNumber');" onchange="checkAadhaarNumExist();">
								<div style="display: flex; justify-content: start;margin-left:15px">
									<select id="authType" name="authType" style="width:49.5%;padding:12px" onchange="handleAuthTypeChange()">
										<option value="">Select Auth Type</option>
										<option value="OTP Authentication">OTP Authentication</option>
										<!-- <option value="Biometric Authentication">Biometric Authentication</option> -->
									</select>
								</div>
								<div style="display: flex; margin-left:250px;">
									<button type="button" id="sendOtpButton" class="hidden"
										style="margin-top: 10px;padding: 12px;background-color: blue; color: white;border: none;cursor: pointer;border-radius: 5px;"
										onclick="sendAadhaarOTP()">Send OTP</button>
								</div>
								<div id="otpDiv" class="hidden">
									<input type="text" id="otpInput" name="otpInput" placeholder="Enter OTP"
										maxlength="6" pattern="\d{6}" style="width: 46.70%;margin:15px;padding:12px"
										title="Please enter a valid 6-digit OTP." required>
										<input type="text" id="phoneNumber" name="phoneNumber"
									placeholder="Enter Phone Number (10 Digits)" maxlength="10" pattern="\d{10}"
									title="Please enter a valid 10-digit Phone number." required
									style="width: 46.70%;margin:15px;padding:12px"
									oninput="handlePhoneNumberChange(this);validateNumber(this,'phoneNumber');">
									<input type="hidden" id="abhaTxn" name="abhaTxn" value="">
									<div style="display: flex;margin-left:250px;">
										<button type="button" id="verifyOtpButton"
											style="padding: 12px;background-color: blue; color: white;border: none;cursor: pointer;border-radius: 5px;"
											onclick="verifyOTP()">Verify OTP</button>
											<!-- <div id="aadhaarTimerId" style="margin-top: 10px;margin-left: 10px;">Resend OTP in <span id="aadhaarTimer"></span></div>
											 <button type="button" id="resendAadhaarOTPId"
										style="margin-left: 5px;display: none;padding: 12px;background-color: #229f60; color: white;border: none;cursor: pointer;border-radius: 5px;"
										onclick="sendAadhaarOTP()">Resend Aadhaar OTP</button> -->
									</div>
								</div>
                                    <div id="abhaBioDiv" class="hidden">
									<input type="text" id="bioMobileNumber" name="bioMobileNumber"
									placeholder="Enter Phone Number (10 Digits)" maxlength="10" pattern="\d{10}"
									title="Please enter a valid 10-digit Phone number." required
									style="width: 46.70%;margin:15px;padding:12px"
									oninput="handlePhoneNumberChange(this);validateNumber(this,'phoneNumber');">
									</div>
								<div id="biometricDiv" class="hidden">
								    <input type="hidden" id="cardTypeBio" name="cardTypeBio" value="">
								    <input type="hidden" id="cardValueBio" name="cardValueBio" value="">
									<textarea id="txtPidData" class="form-control" style="display: none;"></textarea>
									<textarea id="txtPidOptions" class="form-control" style="display: none;"></textarea>
									<textarea id="uidauth_model_bioirisdata" name="uidauth_model_bioirisdata" style="display: none;"></textarea>
									<div style="margin-top: 12px;display: flex; justify-content: start;margin-left:15px;">
										<select id="vendorDevice" name="vendorDevice" style="width:49.5%;padding:12px"
											onchange="handleDeviceSelection()">
											<option value="">Select Vendor Device</option>
											<!-- <option value="Precision">Precision</option>
											<option value="Mantra">Mantra</option> -->
											<option value="Access">StarTek Access Device</option>
										</select>
									</div>
								</div>
								</div>
								<!-- Biometric Form Group -->
								<div class="form-group" id="uidauth_model_bio_div1"
									style="display: none;margin-left: 18px;">
									<label for="uidauth_model_ddlRDS" class="control-label">Available RDService</label>
									<font color="red">*</font>
									<p id="ddlRDS" class="form-control" style="width: 100%;"></p>
									<div style="display: flex; margin-left:250px;margin-top:12px;">
										<button type="button" id="captureBtn" class="btn btn-primary"
											style="display: none;padding: 12px;background-color: blue; color: white;border: none;cursor: pointer;border-radius: 5px;"
											onclick="captureBiometric()">Capture</button>
										<button type="button" id="submitBtn" class="btn btn-primary"
											style="display: none;padding: 12px;background-color: blue; color: white;border: none;cursor: pointer;border-radius: 5px;"
											onclick="submitBiometric()">Submit</button>
										<button type="button" id="cancelBtn" class="btn btn-warning"
											style="padding: 12px;margin-left:12px;background-color: #ecdb61; color: white;border: none;cursor: pointer;border-radius: 5px;"
											onclick="submitCancel()">Cancel</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	<script>
		var MethodCapture = "";
		var MethodInfo = "";
        var finalport="";
		function fn_loading(){
			document.getElementById('loadid').innerHTML="<div id='loadbg'>";
			document.getElementById("loadid").className="loading";
		}
		function fn_removeLoading(){
			document.getElementById("loadid").className="";
			document.getElementById('loadid').innerHTML="<div id=''>";
		}
		function checkConsent() {
			const patientConsent = document.getElementById('patientConsentAcknowledge').checked;
			const operatorConsent = document.getElementById('operatorConsentAcknowledge').checked;
			const aadhaarDiv = document.getElementById('aadhaarDiv');
			const PhoneNumDiv = document.getElementById('PhoneNumDiv');
			if (patientConsent && operatorConsent) {
				aadhaarDiv.style.display = 'block';
				let aadhaarNum = $("#eKYCNum").val();
				if (aadhaarNum != "" && aadhaarNum != undefined) {
					$("#aadhaarNumber").val(aadhaarNum);
					$('#aadhaarNumber').prop('readonly', true);
				}
			}
			else{
				document.getElementById('aadhaarNumber').value = '';
			    document.getElementById('authType').value = '';
		        aadhaarDiv.style.display = 'none';
			}
		}
		function handlePhoneNumberChange(input) {
			const otpMobileDiv = document.getElementById('otpMobileDiv');
			const sendMobileOTPId = document.getElementById('sendMobileOTPId');
			var contactNo = input.value.trim();
			var numericRegex = /^[5-9][0-9]*$/;
			if (!numericRegex.test(contactNo)) {
				input.value = contactNo.replace(/[^0-9]/g, '');
				document.getElementById('phoneNumber').value = "";
				alert("Contact number should contain only numeric characters And It Should strat with 5,6,7,8 & 9");
			} else if (contactNo.length == 10) {
				document.getElementById("sendMobileOTPId").disabled = false;
			}
		}

		function sendMobileOTP() {
			const otpMobileDiv = document.getElementById('otpMobileDiv');
			const sendMobileOTPId = document.getElementById('sendMobileOTPId');
			const mblNO = document.getElementById('phoneNumber');
			const phonenumber = mblNO.value.trim();			  
			$.ajax({
					type: 'POST',
					url: 'aadhaarVerification.do?actionFlag=genMobileotp&randomid='+ Math.random(),
					data: {
						phoneNumber: phonenumber
					},
					success: function (data, textStatus, request) {
						const alert_msg = request.getResponseHeader('alert_msg');
						if (alert_msg === "Success") {
							alert("OTP Sent Successfully to Registered Mobile Number. Please enter OTP to validate.");
							sendMobileOTPId.classList.add("hidden");
							otpMobileDiv.style.display = 'block';
							$('#phoneNumber').prop('readonly', true);
							  resendMobileOTPId = document.getElementById('resendMobileOTPId');
							  resendMobileOTPId.style.display = 'none';
							  timerOTPId = document.getElementById('timerId');
							  timerOTPId.style.display = 'block';
							timer(30);
						} else {
							alert(alert_msg);
						}
					},
					error: function (xhr, status, e) {
						alert("Unable to process the request! Please try again.");
					}
				});
		}
		
		let timerOn = true;
		function timer(remaining) {
			  var m = Math.floor(remaining / 60);
			  var s = remaining % 60;			  
			  m = m < 10 ? '0' + m : m;
			  s = s < 10 ? '0' + s : s;
			  document.getElementById('timer').innerHTML = m + ':' + s;
			  remaining -= 1;			  
			  if(remaining >= 0 && timerOn) {
			    setTimeout(function() {
			        timer(remaining);
			    }, 1000);
			    return;
			  }
			  if(!timerOn) {				  
			    return;
			  }  
			  // Do timeout stuff here
			  resendMobileOTPId = document.getElementById('resendMobileOTPId');
			  resendMobileOTPId.style.display = 'block';
			  timerOTPId = document.getElementById('timerId');
			  timerOTPId.style.display = 'none';			  
			}	
		
		let aadhaarTimerOn = true;
		function aadhaarTimer(remaining) {
			  var m = Math.floor(remaining / 60);
			  var s = remaining % 60;			  
			  m = m < 10 ? '0' + m : m;
			  s = s < 10 ? '0' + s : s;
			  document.getElementById('aadhaarTimer').innerHTML = m + ':' + s;
			  remaining -= 1;			  
			  if(remaining >= 0 && timerOn) {
			    setTimeout(function() {
			        timer(remaining);
			    }, 1000);
			    return;
			  }
			  if(!aadhaarTimerOn) {				  
			    return;
			  }  
			  // Do timeout stuff here
			  resendAadhaarOTPId = document.getElementById('resendAadhaarOTPId');
			  resendAadhaarOTPId.style.display = 'block';
			  aadhaarTimerId = document.getElementById('aadhaarTimerId');
			  aadhaarTimerId.style.display = 'none';			  
			}	

		function verifyMobileOTP() {
			var otpValue = document.getElementById("otpMobileInput")
			var otpVal = otpValue.value.trim();
			const mblNO = document.getElementById('phoneNumber');
			const phonenumber = mblNO.value.trim();
			if (otpVal.length == 6) {
					$.ajax({
						type: 'POST',
						url: 'aadhaarVerification.do?actionFlag=validateMobileotp&randomid='+ Math.random(),
						data: {
							phoneNumber: phonenumber,
							otp: otpVal
						},
						success: function (data, textStatus, request) {
							const alert_msg = request.getResponseHeader('alert_msg');
							if (alert_msg === "SUCCESS") {								
								alert("OTP Verified.");
								const aadhaarDiv = document.getElementById('aadhaarDiv');
								const otpMobileDiv = document.getElementById('otpMobileDiv');
								aadhaarDiv.style.display = 'block';
								let aadhaarNum = $("#eKYCNum").val();
								if (aadhaarNum != "") {
									$("#aadhaarNumber").val(aadhaarNum);
									$('#aadhaarNumber').prop('readonly', true);
								}
								otpMobileDiv.style.display = 'none';
								document.getElementById("sendMobileOTPId").disabled = true;

							} else {
								alert(alert_msg);
							}
						},
						error: function (xhr, status, e) {
							alert("Unable to process the request! Please try again.");
						}
					});
			} else {
				alert("Enter 6 digit OTP");
			}
		}

		function validateNumber(input, id) {
			var number = input.value.trim();
			var numericRegex = /^[0-9]*$/;
			if (!numericRegex.test(number)) {
				input.value = id.replace(/[^0-9]/g, '');
				document.getElementById(id).value = "";
				alert("Field should contain only numeric characters.");
			}
		}

		function handleAuthTypeChange() {
			const authType = document.getElementById("authType").value;
			const otpButton = document.getElementById("sendOtpButton");
			const otpDiv = document.getElementById("otpDiv");
			const biometricDiv = document.getElementById("biometricDiv");
			const abhaBioDiv = document.getElementById("abhaBioDiv");
			const aadhaarNumber = document.getElementById("aadhaarNumber");
			var aadhaarNumberValue = aadhaarNumber.value.trim();
			if (aadhaarNumberValue.length < 12) {
				alert("Enter 12 digit Aadhaar Number");
				document.getElementById('authType').value = '';
			} else if (authType === "OTP Authentication") {
				otpButton.classList.remove("hidden");
				otpDiv.classList.add("hidden");
				biometricDiv.classList.add("hidden");
			} 
			/* else if (authType === "Biometric Authentication") {
				otpButton.classList.add("hidden");
				otpDiv.classList.add("hidden");
				abhaBioDiv.classList.remove("hidden");
				biometricDiv.classList.remove("hidden");
			} */
			else {
				otpButton.classList.add("hidden");
				otpDiv.classList.add("hidden");
				abhaBioDiv.classList.add("hidden");
				biometricDiv.classList.add("hidden");
			}
		}
		//Checking aadhaar already exists or not
		function checkAadhaarNumExist() {
		    const aadharNumber = $("#aadhaarNumber").val();
		    if (aadharNumber.length !== 12 || isNaN(aadharNumber)) {
		        alert("Please enter a valid 12-digit Aadhaar number.");
		        return;
		    }
		    $.ajax({
		        type: "GET",
		        url: "aadhaarVerification.do",
		        data: {
		            actionFlag: "checkIfAadhaarExists",
		            aadharNumber: aadharNumber
		        },
		        success: function (response) {
		            fn_removeLoading();
		            var cleanResponse = response.replace(/['"]+/g, '').trim();
		            if (cleanResponse === "Y") {
		                alert("ABHA already exists for this Aadhaar. Please enter another Aadhaar number.");
		                $("#aadhaarNumber").val("").focus();
		                return;
		            }
		        },
		        error: function () {
		            fn_removeLoading();
		            alert("Unable to check Aadhaar. Please try again.");
		        }
		    });
		}
		//Function for sending otp to aadhaar registered mobile number
		function sendAadhaarOTP() {
			fn_loading();
			const aadharNumber = $("#aadhaarNumber").val();
			if (aadharNumber.length !== 12 || isNaN(aadharNumber)) {
				alert("Please enter a valid 12-digit Aadhaar number.");
				return;
			}
			$.ajax({
					type: 'POST',
					url: 'aadhaarVerification.do?actionFlag=genAbhaotp&randomid='+ Math.random(),
					data: {
						aadharNum: aadharNumber
					},
					success: function (data, textStatus, request) {
						const alert_msg = request.getResponseHeader('alert_msg');
						fn_removeLoading();
						try{
							const responseJson = JSON.parse(alert_msg);
							var status = responseJson.statusCode;
							if (status == undefined) {
							    const alert_msg = request.getResponseHeader('alert_msg');
							    try {
							        const responseJson = JSON.parse(alert_msg);
							        if (responseJson.error) {
							            alert(responseJson.error);
							        } else {
							            alert(alert_msg);
							        }
							    } catch (e) {
							        alert(alert_msg);
							    }
							}
							if(status == '200'){
							 if (responseJson.message) {
								 alert(responseJson.responseObject.message);
								 $("#abhaTxn").val(responseJson.responseObject.txnId);
								 $('#aadhaarNumber').prop('readonly', true);
								 $("#sendOtpButton").addClass("hidden");
								 $("#otpDiv").removeClass("hidden");	
							    }
							}else{
								alert(responseJson.responseObject.message);
								 $("#abhaTxn").val(responseJson.txnId);
								 $('#aadhaarNumber').prop('readonly', false);
								 //$("#sendOtpButton").addClass("hidden");
							}
						}
						catch (error) {
						    console.error("Error parsing JSON:", error);
						    fn_removeLoading();
						}
					},
					error: function (xhr, status, e) {
						alert("Unable to process the request! Please try again.");
						fn_removeLoading();
					}
				});
		}
		//Function for verifying the OTP
		function verifyOTP() {
			const mobileNum = $("#phoneNumber").val();
			var cardType = document.querySelector('input[name="card_type"]:checked').value;
			//pravalika
			var wapFamilyNo = '';
			if(cardType === 'DJ')
				wapFamilyNo = readCardData1();
			else
			   wapFamilyNo=readCardData();
			if (mobileNum.length !== 10 || isNaN(mobileNum)) {
				alert("Please enter a valid 10-digit Mobile Number.");
				return;
			}
			const otp = $("#otpInput").val();
			if (otp.length !== 6 || isNaN(otp)) {
				alert("Please enter a valid 6-digit OTP.");
				return;
			}		
			const aadharNumber = $("#aadhaarNumber").val();
			if (aadharNumber.length !== 12 || isNaN(aadharNumber)) {
				alert("Please enter a valid 12-digit Aadhaar number.");
				return;
			}
			fn_loading();
			const txnId = $("#abhaTxn").val();
			const abhaGenReg = document.getElementById("abhaGenReg").value;
			
				$.ajax({
					type: 'POST',
					url: 'aadhaarVerification.do?actionFlag=validateAbhaotp&randomid='+ Math.random(),
					data: {
						mobileNumber: mobileNum,
						abhaTxn: txnId,
						aadharNum_otp: otp,
						aadharNum: aadharNumber,
						cardType: cardType,
						cardValue: wapFamilyNo,
						abhaGenReg: abhaGenReg,
					},
					success: function (data, textStatus, request) {
						//alert("request::: "+request);//Chandana - need to remove this alert
						console.log("request::::: "+request);
						const alert_msg = request.getResponseHeader('alert_msg');	
						const abhaNo = request.getResponseHeader('abhaNo');
						const abhaDone = request.getResponseHeader('abhaDone');
						fn_removeLoading();
						if(alert_msg == "SUCCESS"){
							alert("Account created successfully with ABHA ID: " + abhaNo);
							$('#viewekycModal').modal('hide');
							document.getElementById('eKYCTr').style.display = 'none';
							retrieveDetails();														
						}
						else{
							alert(abhaNo);
							submitCancel();							
						}
						
					},
					error: function (xhr, status, e) {
						alert("Unable to process the request! Please try again.");
						fn_removeLoading();
					}
				});
		}

		function handleDeviceSelection() {
			const selectedDevice = document.getElementById("vendorDevice").value;
			if (selectedDevice === "Precision") {
				discoverRDService();
			} else if (selectedDevice === "Mantra") {
				discoverRDServiceMantra();
			} else if (selectedDevice === "Access") {
				discoverRDServiceAccess();
			}
		}
		
		function submitBiometric() {
			var cardTypeBio = document.querySelector('input[name="card_type"]:checked').value;
			$("#cardTypeBio").val(cardTypeBio);
			const aadharNumber = $("#aadhaarNumber").val();
			var wapFamilyNo=readCardData();
			$("#cardValueBio").val(wapFamilyNo);
			
			if (aadharNumber.length !== 12 || isNaN(aadharNumber)) {
				alert("Please enter a valid 12-digit Aadhaar number.");
				return;
			}
			const mobileNum = $("#bioMobileNumber").val();
			if (mobileNum.length !== 10 || isNaN(mobileNum)) {
				alert("Please enter a valid 10-digit Mobile Number.");
				return;
			}
			fn_loading();
			const txtPidData = $("#txtPidData").val();
			$("#uidauth_model_bioirisdata").val(btoa(txtPidData));
			console.log("PID::" + btoa(txtPidData));
			if (txtPidData != "" && txtPidData.length >= 10) {
				$.ajax({
						type: 'POST',
						url: 'aadhaarVerification.do?actionFlag=abhaBioAuth&randomid='+Math.random(),
						data: $("form#uidauth_model_form").serialize(),
						success: function (data, textStatus, request) {
							fn_removeLoading();							
							const alert_msg = request.getResponseHeader('alert_msg');
							const abhaNo = request.getResponseHeader('abhaNo');
							const abhaDone = request.getResponseHeader('abhaDone');
							$('#abhaNoRet').val(abhaNo);
							$('#abhaDone').val(abhaDone);			
							if (alert_msg == "SUCCESS") {
								alert("Account created successfully with ABHA ID: " + abhaNo);
								submitCancel();
								$('#viewekycModal').modal('hide');
								document.getElementById('eKYCTr').style.display = 'none';
								retrieveDetails();
							} else {
								alert(abhaNo);
								submitCancel();
								//$('#viewekycModal').modal('hide');
							}
						},
						error: function (xhr, status, e) {
							alert("unable to process the request!.please try again.");

						}
					});
			} else {
				alert("Please enter valid details");
				return false;
			}
		}

		function discoverRDService() {
			const rdServiceDiv = document.getElementById("uidauth_model_bio_div1");
			const ddlRDS = document.getElementById("ddlRDS");
			const captureBtn = document.getElementById("captureBtn");

			var primaryUrl = "http://127.0.0.1:";
			url = "";
			for (var i = 11100; i <= 11120; i++) {
				var res;
				$.support.cors = true;
				var httpStaus = false;
				var xmlstr = "";

				$.ajax({
					type: "RDSERVICE",
					async: false,
					crossDomain: true,
					url: primaryUrl + i.toString(),
					contentType: "text/xml; charset=utf-8",
					processData: false,
					cache: false,
					async: false,
					crossDomain: true,

					success: function (data) {
						try {
							xmlstr = data.xml ? data.xml : (new XMLSerializer()).serializeToString(data);
							data = xmlstr;
							httpStaus = true;
							res = {
								httpStaus: httpStaus,
								data: data
							};
							finalUrl = primaryUrl + i.toString();
							var $doc = $.parseXML(xmlstr);
							var CmbData1 = $($doc).find('RDService').attr(
								'status');
							var CmbData2 = $($doc).find('RDService').attr(
								'info');
							if (CmbData1 === "READY") {
								rdServiceDiv.style.display = "block";
								captureBtn.style.display = "block";
							} else {
								rdServiceDiv.style.display = "block";
							}
							ddlRDS.innerHTML = 'Port : ' + i.toString()+ ' Status : ' + CmbData1 + '(' + CmbData2 + ')';
						} catch (e) {
							console.log(e);
						}
					},
					error: function (msg) {
						var result = msg.status + msg.statusText + msg.responseText;
						console.log(result);
					}
				});

				if (xmlstr != null) {
					if (xmlstr.indexOf('Precision') > 0) {
						discoverFirstNode(i);
						break;
					}

				}

			}
			return res;
		}

		function discoverFirstNode(PortNo) {
			$('#txtDeviceInfo').val('');
			$('#txtPidOptions').val('');
			$('#txtPidData').val('');

			var primaryUrl = "http://127.0.0.1:";
			url = "";
			var verb = "RDSERVICE";
			var err = "";
			var res;
			$.support.cors = true;
			var httpStaus = false;
			var jsonstr = "";
			var data = new Object();
			var obj = new Object();

			$.ajax({
					type: "RDSERVICE",
					async: false,
					crossDomain: true,
					url: primaryUrl + PortNo,
					contentType: "text/xml; charset=utf-8",
					processData: false,
					cache: false,
					async: false,
					crossDomain: true,
					success: function (data) {
						var xmlstr = data.xml ? data.xml
							: (new XMLSerializer())
								.serializeToString(data);
						//alert(xmlstr);
						data = xmlstr;
						//alert(data);
						httpStaus = true;
						res = {
							httpStaus: httpStaus,
							data: data
						};
						//alert(data);		
						$('#txtRDSInfo').val(data);

						var $doc = $.parseXML(data);

						for (var j = 0; j < 2; j++) {
							if ($($doc).find('Interface').eq(j).attr('id') == 'CAPTURE') {
								MethodCapture = $($doc).find('Interface')
									.eq(j).attr('path');
							} else if ($($doc).find('Interface').eq(j)
								.attr('id') == 'DEVICEINFO') {
								MethodInfo = $($doc).find('Interface')
									.eq(j).attr('path');
							}
						}
					},
					error: function (msg) {
						var result = msg.status + msg.statusText
							+ msg.responseText;
					}
				});

			return res;

		}

		function CapturePrecision() {
			var fCount = "1";
			var fType = "2";
			var format = 0;
			var pidver = "2.0";
			var timeout = 20000;
			var env = "P"; // P : Production , PP : Pre Production
			var lock = "";
			var posh = "UNKNOWN";
			var txtWadh = "E0jzJ/P8UopUHAieZn8CKqS4WPMi5ZSYXgfnlfkWjrc=";
			finalUrl = "http://127.0.0.1:" + finalport;

			var XML = '<?xml version="1.0"?> <PidOptions ver="1.0"> <Opts fCount="' + fCount + '" fType="' + fType + '" format="' + format + '" pidVer="' + pidver + '" timeout="' + timeout + '" wadh="' + txtWadh + '" env="' + env + '" posh="' + posh + '" /> <CustOpts> <Param name="LockingKey" value="' + lock + '" /> </CustOpts> </PidOptions>';
			var res;
			$.support.cors = true;
			var httpStaus = false;
			$
				.ajax({
					type: "CAPTURE",
					async: false,
					crossDomain: true,
					url: finalUrl + MethodCapture,
					data: XML,
					contentType: "text/xml; charset=utf-8",
					processData: false,
					success: function (data) {
						var xmlstr = data.xml ? data.xml
							: (new XMLSerializer())
								.serializeToString(data);
						//alert(xmlstr);
						data = xmlstr;
						//alert(data);
						httpStaus = true;
						res = {
							httpStaus: httpStaus,
							data: data
						};

						$('#txtPidData').val(data);

						//alert("Hi Buddy : "+$("#txtPidData").val());

						$('#txtPidOptions').val(XML);

						var $doc = $.parseXML(data);
						var Message = $($doc).find('Resp').attr('errInfo');

						$("#btn_uidauth_bio_cap_id_precision").hide();
						$("#btn_uidauth_bio_validate").show();
					},
					error: function (msg) {
						var result = msg.status + msg.statusText
							+ msg.responseText;
						alert("Precision - Scanner not connected");

						$("#btn_uidauth_bio_cap_id_precision").show();
						$("#btn_uidauth_bio_validate").hide();
					}
				});

			return res;

		}

		function discoverRDServiceMantra() {
			const
				rdServiceDiv = document.getElementById("uidauth_model_bio_div1");
			const
				ddlRDS = document.getElementById("ddlRDS");
			const
				captureBtn = document.getElementById("captureBtn");
			$("#ddlRDS").empty();
			var primaryUrl = "https://127.0.0.1:";
			url = "";
			//i = "8005";
			i="11105";
			var res;
			$.support.cors = true;
			var httpStaus = false;
			var xmlstr;
			$
				.ajax({
					type: "RDSERVICE",
					async: false,
					crossDomain: true,
					url: primaryUrl + i.toString(),
					contentType: "text/xml; charset=utf-8",
					processData: false,
					cache: false,
					async: false,
					crossDomain: true,
					success: function (data) {
						try {
							const
								parser = new DOMParser();
							const
								xmlDoc = parser.parseFromString(data,
									"text/xml");
							if (xmlDoc.getElementsByTagName("parsererror").length > 0) {
								console.error("Error parsing XML");
							} else {
								const
									serializer = new XMLSerializer();
								xmlstr = serializer
									.serializeToString(xmlDoc);
								console.log(xmlstr);
							}
							data = xmlstr;
							httpStaus = true;
							res = {
								httpStaus: httpStaus,
								data: data
							};
							finalUrl = primaryUrl + i.toString();
							var $doc = $.parseXML(xmlstr);
							var CmbData1 = $($doc).find('RDService').attr(
								'status');
							var CmbData2 = $($doc).find('RDService').attr(
								'info');
							if (CmbData1 === "READY") {
								rdServiceDiv.style.display = "block";
								captureBtn.style.display = "block";
							} else {
								rdServiceDiv.style.display = "block";
							}
							ddlRDS.innerHTML = 'Port : ' + i.toString()
								+ ' Status : ' + CmbData1 + '('
								+ CmbData2 + ')';
						} catch (e) {
						}
					},
					error: function (msg) {
						var result = msg.status + msg.statusText
							+ msg.responseText;
					}
				});

			if (xmlstr != null) {
				if (xmlstr.indexOf('Mantra') > 0) {
					discoverFirstNodeMantra();
				}

			}
			return res;
		}

		function discoverFirstNodeMantra() {
			$('#txtDeviceInfo').val('');
			$('#txtPidOptions').val('');
			$('#txtPidData').val('');
			var primaryUrl = "https://127.0.0.1:";
			url = "";
			i = "11105";
			var res;
			$.support.cors = true;
			var httpStaus = false;
			var xmlstr;
			$.ajax({
					type: "RDSERVICE",
					async: false,
					crossDomain: true,
					url: primaryUrl + i.toString(),
					contentType: "text/xml; charset=utf-8",
					processData: false,
					cache: false,
					async: false,
					crossDomain: true,
					success: function (data) {
						const parser = new DOMParser();
						const xmlDoc = parser.parseFromString(data, "text/xml");
						if (xmlDoc.getElementsByTagName("parsererror").length > 0) {
							console.error("Error parsing XML");
						} else {
							const serializer = new XMLSerializer();
							xmlstr = serializer.serializeToString(xmlDoc);
							console.log(xmlstr);
						}
						data = xmlstr;
						httpStaus = true;
						res = {
							httpStaus: httpStaus,
							data: data
						};
						$('#txtRDSInfo').val(data);
						var $doc = $.parseXML(data);
						for (var j = 0; j < 2; j++) {
							if ($($doc).find('Interface').eq(j).attr('id') == 'CAPTURE') {
								MethodCapture = $($doc).find('Interface')
									.eq(j).attr('path');
							} else if ($($doc).find('Interface').eq(j)
								.attr('id') == 'DEVICEINFO') {
								MethodInfo = $($doc).find('Interface')
									.eq(j).attr('path');
							}
						}
					},
					error: function (msg) {
						var result = msg.status + msg.statusText
							+ msg.responseText;
					}
				});
			return res;
		}

		function CaptureMantra() {
			const captureBtn = document.getElementById("captureBtn");
			const submitBtn = document.getElementById("submitBtn");

			var fCount = "1";
			var fType = "2";
			var format = 0;
			var pidver = "2.0";
			var timeout = 20000;
			var env = "P"; // P : Production , PP : Pre Production
			var lock = "";
			var posh = "UNKNOWN";
			var Icount = "0";
			var Pcount = "0";
			var DType = "0";
			var DemoFinalString = '';
			var txtWadh = "E0jzJ/P8UopUHAieZn8CKqS4WPMi5ZSYXgfnlfkWjrc=";
			finalUrl = "https://127.0.0.1:11105";

			var XML = '<?xml version="1.0"?> <PidOptions ver="1.0"> <Opts fCount="' + "1" + '" fType="' + "2" + '" iCount="' + "0" + '" pCount="' + "0" + '" format="' + "0" + '"   pidVer="' + "2.0" + '" timeout="' + "10000" + '" wadh="E0jzJ/P8UopUHAieZn8CKqS4WPMi5ZSYXgfnlfkWjrc=" posh="UNKNOWN" env="' + "P" + '" /> '
				+ DemoFinalString
				+ '<CustOpts><Param name="mantrakey" value="' + lock + '" /></CustOpts></PidOptions>';

			/*  if(authType== "02"){
			   var XML = '<?xml version="1.0"?><PidOptions ver="1.0"><Opts fCount="' + fCount + '" fType="' + fType + '" iCount="' + Icount + '" pCount="' + Pcount + '" format="' + format + '"   pidVer="' + pidver + '" timeout="' + timeout + '" posh="UNKNOWN" env="' + env + '" /> ' + DemoFinalString + '<CustOpts><Param name="mantrakey" value="'+lock +'" /></CustOpts></PidOptions>';
			}
			else
			{	
			   var XML = '<?xml version="1.0"?> <PidOptions ver="1.0"> <Opts fCount="' + "1" + '" fType="' + "2" + '" iCount="' + "0" + '" pCount="' + "0" + '" format="' + "0" + '"   pidVer="' + "2.0" + '" timeout="' + "10000" + '" wadh="' + txtWadh + '" posh="UNKNOWN" env="' + "P" + '" /> ' + DemoFinalString + '<CustOpts><Param name="mantrakey" value="'+lock +'" /></CustOpts></PidOptions>';
			} */
			var res;
			$.support.cors = true;
			var httpStaus = false;
			$
				.ajax({
					type: "CAPTURE",
					async: false,
					crossDomain: true,
					url: finalUrl + MethodCapture,
					data: XML,
					contentType: "text/xml; charset=utf-8",
					processData: false,
					success: function (data) {
						// Parse the XML string to an XML DOM object
						const
							parser = new DOMParser();
						const
							xmlDoc = parser.parseFromString(data, "text/xml");
						// Check if the XML document was parsed correctly
						if (xmlDoc.getElementsByTagName("parsererror").length > 0) {
							console.error("Error parsing XML");
						} else {
							// Serialize the XML DOM object back to a string
							const
								serializer = new XMLSerializer();
							xmlstr = serializer.serializeToString(xmlDoc);
							console.log(xmlstr);
						}
						data = xmlstr;
						httpStaus = true;
						res = {
							httpStaus: httpStaus,
							data: data
						};
						$('#txtPidData').val(data);
						var $doc = $.parseXML(data);
						var Message = $($doc).find('Resp').attr('errInfo');
						if (Message == "Success.") {
							alert(Message);
							captureBtn.style.display = "none";
							submitBtn.style.display = "block";
						} else {
							alert(Message);
						}
					},
					error: function (msg) {
						var result = msg.status + msg.statusText
							+ msg.responseText;
						alert(result);
					}
				});
			return res;
		}
		
		function discoverRDServiceAccess()
		   {
			   var primaryUrl = "https://127.0.0.1:";
			   var tempcount = 0;
			   url = "";
			   
			   $("#ddlRDS").empty();
			   var stopLoop = false; 
			   const rdServiceDiv = document.getElementById("uidauth_model_bio_div1");
			const ddlRDS = document.getElementById("ddlRDS");
			const captureBtn = document.getElementById("captureBtn");
				   for (var i = 11100; i <= 11120 && !stopLoop; i++)
					{
				   var verb = "RDSERVICE";
				   var err = "";
				   var res;
				   $.support.cors = true;
				   var httpStaus = false;
				   var jsonstr="";
				   var data = new Object();
				   var obj = new Object();
				   var xmlstr;						   
				   $.ajax({
						 type: "RDSERVICE",
					   async: false,
					   crossDomain: true,
					   url: primaryUrl + i.toString(),
					   contentType: "text/xml; charset=utf-8",
					   processData: false,
					   cache: false,
					   async:false,
					   crossDomain:true,
					   success: function (data) {
					   try{
						   console.log(data);
						   var $doc = "";
						   if (typeof data === 'string') {
			                    $doc = $.parseXML(data);  // Parse if the data is a string
			                } else {
			                    $doc = data;  // Use the document directly if it's already an XML document
			                }
						   var CmbData1 =  $($doc).find('RDService').attr('status');
						   var CmbData2 =  $($doc).find('RDService').attr('info');
						 							   
						   if(CmbData1 == "READY"){
							   finalport = i.toString();
							   tempcount++;
							   $("#ddlRDS").append('<option value='+i.toString()+'>Port : '+i.toString()+' Status : ' + CmbData1 +'('+CmbData2+')</option>');
			                    stopLoop = true; 
								rdServiceDiv.style.display = "block";
							    captureBtn.style.display = "block";
						   }
						   else {
							rdServiceDiv.style.display = "block";
						}
	                   }
						   catch(e){}
					   },
					   error: function(msg) {
						   var result = msg.status + msg.statusText + msg.responseText;					   
					   }
				   });
			   }
		   }		 
		  function CaptureAccess() {
			    const captureBtn = document.getElementById("captureBtn");
				const submitBtn = document.getElementById("submitBtn");

			    var fCount = "1";
			    var fType = "2";
			    var format = 0;
			    var pidVer = "2.0";
			    var timeout = 20000;
			    var env = "P";
			    var lock = "";
			    var posh = "UNKNOWN";
			    var txtWadh = "RZ+k4w9ySTzOibQdDHPzCFqrKScZ74b3EibKYy1WyGw="
			    var xml;
			    var xmlStr="";
			    
			    xml = '<?xml version="1.0"?> <PidOptions ver="1.0"> <Opts fCount="' + fCount + '" fType="' + fType + '" format="' + format + '" pidVer="' + pidVer + '" timeout="' + timeout + '" wadh="' + txtWadh + '" env="' + env + '" posh="' + posh + '" /> <CustOpts> <Param name="accesskey" value="' + lock + '" /> </CustOpts> </PidOptions>';
               
			    var finalUrl = "https://127.0.0.1:"+finalport+"/rd/capture?ts=" + new Date().getTime();
			    var httpStatus = false;
			    var res;
			    $.support.cors = true;

			    $.ajax({
			        type: "CAPTURE",
			        async: false,
			        crossDomain: true,
			        url: finalUrl,
			        data: xml,
			        contentType: "text/xml",
			        processData: false,
			        beforeSend: function(xhr) {
			            xhr.setRequestHeader("X-HTTP-Method-Override", "CAPTURE");
			        },
			        success: function (data) {	
			        	console.log(data);
			        	console.log($.parseXML(data));
			        	const serializer = new XMLSerializer();
			        	xmlStr = serializer.serializeToString(data);
						console.log(xmlStr);
			            httpStatus = true;
			            res = { httpStatus: httpStatus, data: xmlStr };
			            $('#txtPidData').val(xmlStr);
			            console.log("txtPieData::"+$('#txtPidData').val());
			            $('#txtPidOptions').val(xml);
			            console.log("txtPidOptions::"+$('#txtPidOptions').val());

			            var $doc = $.parseXML(xmlStr);
						var Message =  $($doc).find('Resp').attr('errInfo');
			            if (Message != "") {
			                alert(Message);			                
			            } else {
			            	alert("Captured");
			            	captureBtn.style.display = "none";
							submitBtn.style.display = "block";
			            }
			        },
			        error: function (msg) {
			            var result = msg.status + " " + msg.statusText + " " + msg.responseText;
			            alert(result);
			            $("#btn_uidauth_bio_cap_id_access").show();
			            $("#btn_uidauth_bio_validate").hide();
			        }
			    });
			    return res;
			} 

		// Function to handle biometric capture
		function captureBiometric() {
			var wapFamilyNo = "";
			const selectedDevice = document.getElementById("vendorDevice").value;
			if (selectedDevice === "Precision") {
				CapturePrecision();
			} else if (selectedDevice === "Mantra") {
				CaptureMantra();
			}			
			else if (selectedDevice === "Access") {
				CaptureAccess();
			}
		}	

		function submitCancel() {
		    const biometricDiv = document.getElementById("biometricDiv");
		    biometricDiv.classList.add("hidden");
		    const abhaBioDiv = document.getElementById("abhaBioDiv");
		    abhaBioDiv.classList.add("hidden");
		    const aadhaarInput = document.getElementById('aadhaarNumber');
		    aadhaarInput.removeAttribute("readonly");
		    document.getElementById('otpInput').value = '';
		    document.getElementById('uidauth_model_bio_div1').style.display = 'none';
		}

		function submitCancel12() {
			const biometricDiv = document.getElementById("biometricDiv");
			biometricDiv.classList.add("hidden");
			const abhaBioDiv = document.getElementById("abhaBioDiv");
			abhaBioDiv.classList.add("hidden");
			document.getElementById('aadhaarNumber').value = '';
			document.getElementById('authType').value = '';
			document.getElementById('uidauth_model_bio_div1').style.display = 'none';
		}

		function submitCancelForOTP() {
			const PhoneNumDiv = document.getElementById('PhoneNumDiv');
			PhoneNumDiv.style.display = 'none';
			const aadhaarDiv = document.getElementById('aadhaarDiv');
			aadhaarDiv.style.display = 'none';
		}		
		
		function closeConsent() {
		    $("#consentModal").hide();
		    $("#patientConsentAcknowledge").prop("checked", false);
		    $("#operatorConsentAcknowledge").prop("checked", false);
		    $("#abhaOption").prop("checked", false);

		    $("#aadhaarDiv").hide();
		    $("#aadhaarNumber").val("").prop("readonly", false);
		    $("#authType").val("");

		    $("#sendOtpButton").addClass("hidden");
		    $("#otpDiv").addClass("hidden");
		    $("#otpInput").val("");
		    $("#phoneNumber").val("").prop("readonly", false);
		    /* $("#biometricDiv").addClass("hidden");
		    $("#abhaBioDiv").addClass("hidden");
		    $("#vendorDevice").val(""); */
		}
	</script>
</body>

</html>