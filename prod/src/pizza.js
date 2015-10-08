(function() {

    var pizzaNextButton = $('#pizza_next');
    var drinkNextButton = $('#drink_next');
    var summaryNextButton = $('#summary_next');
    var stepOne = $('#step1');
    var stepTwo = $('#step2');
    var stepThree = $('#step3');
    var stepFour = $('#step4');

    var selectedPizza = '';
    var selectedDrinks = [];

    pizzaNextButton.click(function() {
        selectedPizza = $("input:radio[name ='pizza']:checked").val();
        console.log(selectedPizza);
        if(typeof selectedPizza === 'undefined') {
            alert("Please select a pizza!");
            return;
        }
        stepOne.hide();
        stepTwo.show();
    });

    drinkNextButton.click(function() {
        stepTwo.hide();
        stepThree.show();
        rememberSelectedDrinks();
        addPizzaAndDrinkToSummary();
    });

    function rememberSelectedDrinks() {
        selectedDrinks = [];
        stepTwo.find('input').each(function (index) {
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

    summaryNextButton.click(function() {
        stepThree.hide();
        stepFour.show();
    });

})();
