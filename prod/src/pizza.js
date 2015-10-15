(function() {

    // Wizard steps
    var steps = {
        enterPhoneNumber : $('#step-enterPhoneNumber'),
        confirmAddress : $('#step-confirmAddress'),
        enterAddress : $('#step-enterAddress'),
        selectPizza : $('#step-selectPizza'),
        selectDrinks : $('#step-selectDrinks'),
        summary : $('#step-summary'),
        confirmation : $('#step-confirmation')
    };

    // UI state
    var currentStepOnPage = $('#currentStep');
    var currentStep;
    setCurrentStep(steps.enterPhoneNumber);

    // Collected data
    var phoneNumber = '';
    var selectedPizza = '';
    var selectedDrinks = [];

    steps.enterPhoneNumber.find('.next').click(function() {
        var KNOWN_NUMBER = '0791111111';

        phoneNumber = $('#phoneNumber').val();

        if(phoneNumber.trim() === '') {
            alert('Please enter a phone number');
            return;
        }

        if(phoneNumber === KNOWN_NUMBER) {
            goToStep(steps.confirmAddress);
        } else {
            goToStep(steps.enterAddress);
        }
    });

    steps.confirmAddress.find('.yes').click(function() {
        goToStep(steps.selectPizza);
    });

    steps.confirmAddress.find('.no').click(function() {
        goToStep(steps.enterAddress);
    });

    steps.enterAddress.find('.next').click(function() {
        goToStep(steps.selectPizza);
    });

    steps.selectPizza.find('.next').click(function() {
        selectedPizza = $("input:radio[name ='pizza']:checked").val();
        if(typeof selectedPizza === 'undefined') {
            alert("Please select a pizza!");
            return;
        }
        goToStep(steps.selectDrinks);
    });

    steps.selectDrinks.find('.next').click(function() {
        rememberSelectedDrinks();
        addPizzaAndDrinkToSummary();
        goToStep(steps.summary);

        function rememberSelectedDrinks() {
            selectedDrinks = [];
            steps.selectDrinks.find('input').each(function (index) {
                var element = $(this);
                if (element.is(':checked')) {
                    selectedDrinks.push(element.val());
                }
            });
            if(selectedDrinks.length === 0) {
                selectedDrinks.push('none');
            }
        }

        function addPizzaAndDrinkToSummary() {
            $('#summary_pizza').text(selectedPizza);
            $('#summary_drinks').text(selectedDrinks.join(", "));
            $('#confirmation_pizza').text(selectedPizza);
            $('#confirmation_drinks').text(selectedDrinks.join(", "));
        }
    });

    steps.summary.find('.next').click(function() {
       goToStep(steps.confirmation);
    });

    function goToStep(toStep) {
        currentStep.hide();
        toStep.show();
        setCurrentStep(toStep);
    }

    function setCurrentStep(step) {
        currentStep = step;
        currentStepOnPage.text(step.attr('id'));
    }

})();
