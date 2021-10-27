package frontend;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Fego
 */
public abstract class Validate {
    
    private static char c;
    //variables for indicating what to validate in a text component
    final static int ONLY_LETTERS = 0;
    final static int ONLY_DIGITS = 1;
    final static int TEXT_LENGTH = 2;
    final static int DIGITS_LENGTH = 3;
    final static int LETTERS_LENGTH = 4;
    final static int REGEX = 5;
    final static int NO_VALIDATION = 6;
    
    //Border variables
    static final Border OUTER = BorderFactory.createLineBorder(new Color(255, 0, 0), 1, true);
    static final Border INNER = new EmptyBorder(8, 8, 8, 8);
    static final Border ERROR_BORDER = new CompoundBorder(OUTER,INNER);
       
    public final static boolean hasOnlyLetters(JTextComponent field){
        boolean state = true;
        StringBuilder text = new StringBuilder(field.getText().trim());
        
        for(int i = 0; i < text.length(); i++){
            c = text.charAt(i);
            if(!(Character.isLetter(c))){
                state = false;
                break;
            }           
        }               
        return state;
    }
    
    public final static boolean hasOnlyDigits(JTextComponent field){
        boolean state = true;
        StringBuilder text = new StringBuilder(field.getText().trim());
        
        for(int i = 0; i < text.length(); i++){
            c = text.charAt(i);
            if(!(Character.isDigit(c))){
                state = false;
                break;
            }           
        }  
        return state;
    }
    
    public static final boolean charLength(JTextComponent field, int limit){
        boolean state = true;
        String text = field.getText().trim();
        
        if(text.length() != limit){
            state = false;
        }
        
        return state;
    }
    
    public final static boolean matchesRegex(JTextComponent field, String regex){
        boolean state = true;
        String text = field.getText().trim();
        
            if(!text.matches(regex)){
                state = false;
            }
        return state;
    }
    
    //FOR TEXTFIELDS
    //for a single complusory field to make sure it is filled
    public static boolean validateSingleCompulsoryTextField(
        JTextComponent field, String initialText,int type,Integer limit,String regex, Border borderDefault
    ){
        boolean state = true;

        //validate data
        if (!field.getText().trim().isEmpty() || !field.getText().equals(initialText)) {
            switch(type){
                case ONLY_LETTERS: 
                    if (!hasOnlyLetters(field)) {
                        state = false;
                        field.setBorder(ERROR_BORDER);
                    } else {
                        field.setBorder(borderDefault);
                    }
                break;

                case ONLY_DIGITS: 
                    if (!hasOnlyDigits(field)) {
                        state = false;
                        field.setBorder(ERROR_BORDER);
                    } else {
                        field.setBorder(borderDefault);
                    }
                break;

                case TEXT_LENGTH: 
                    if(limit == null || limit <= 0){
                        //throe exception
                        throw new IllegalArgumentException("invalid value for limit");
                    }
                    else{
                        if (!charLength(field,limit)) {
                            state = false;
                            field.setBorder(ERROR_BORDER);
                        } else {
                            field.setBorder(borderDefault);
                        }
                    }
                break;
                
                case DIGITS_LENGTH: 
                    if(limit == null || limit <= 0){
                        //throw exception
                        throw new IllegalArgumentException("invalid value for limit");
                    }
                    else{
                        if (hasOnlyDigits(field) && charLength(field,limit)) {
                            field.setBorder(borderDefault);
                        } else {
                            state = false;
                            field.setBorder(ERROR_BORDER);
                        }
                    }
                break;
                
                case LETTERS_LENGTH: 
                    if(limit == null || limit <= 0){
                        //throw exception
                        throw new IllegalArgumentException("invalid value for limit");
                    }
                    else{
                        if (!hasOnlyLetters(field) && !charLength(field,limit)) {
                            state = false;
                            field.setBorder(ERROR_BORDER);
                        } else {
                            field.setBorder(borderDefault);
                        }
                    }
                break;
                
                case REGEX: 
                    if(regex == null || regex.trim().isEmpty()){
                        //throe exception
                        throw new IllegalArgumentException("no regex pattern for matching");
                    }
                    else{
                        if (!(matchesRegex(field, regex))) {
                            state = false;
                            field.setBorder(ERROR_BORDER);
                        } else {
                            field.setBorder(borderDefault);
                        }
                        
                    }
                break;

                case NO_VALIDATION: 
                    if (field.getText().trim().isEmpty() || field.getText().equals(initialText)){
                            state = false;
                            field.setBorder(ERROR_BORDER);
                    } 
                    else{
                        field.setBorder(borderDefault);
                    }
                break; 

                default:
                    throw new IllegalArgumentException("Invalid type of validation passed");
            }
     
        } else {
            state = false;
            field.setBorder(ERROR_BORDER);
        }
       
        return state;
    }
    
    //for a single optional fields to check if filled
    /**For Validating text fields
     * @param field The text field to be validated
     * @param initialText the initial text in the text field
     * @param type type of validation
     * @param limit if there is a limit for the number of characters that should be in the text field
     * @param regex used for matching patterns
     * @param borderDefault default border to be set if the value is correct else a red border is set for the text field
     * @return values from the text field that is not the initial value, if the value is the initial it returns 'NA' else 
     * if value is incorrect it returns invalid
     */
    public static String validateSingleOptionalTextField(
        JTextComponent field, String initialText,int type,Integer limit, String regex, Border borderDefault
    ){
        String val;
        
        if (field.getText().trim().isEmpty() || field.getText().equals(initialText)) {
            val = "NA";
        }
        else{
            switch(type){
                case ONLY_LETTERS: 
                    if(hasOnlyLetters(field)){
                        val = field.getText();
                        field.setBorder(borderDefault);
                    }
                    else{
                        val = "invalid";
                        field.setBorder(ERROR_BORDER);
                    }
                break;

                case ONLY_DIGITS: 
                    if(hasOnlyDigits(field)){
                        val = field.getText();
                        field.setBorder(borderDefault);
                    }
                    else{
                        val = "invalid";
                        field.setBorder(ERROR_BORDER);
                    }
                break;

                case TEXT_LENGTH: 
                    if(limit == null || limit <= 0){
                        throw new IllegalArgumentException("Invalid value for limit");
                    }
                    else{
                        if(charLength(field,limit)){
                            val = field.getText();
                            field.setBorder(borderDefault);
                        }
                        else{
                            val = "invalid";
                            field.setBorder(ERROR_BORDER);
                        }                      
                    }
                break;
                
                case DIGITS_LENGTH: 
                    if(limit == null || limit <= 0){
                        throw new IllegalArgumentException("Invalid value for limit");
                    }
                    else{
                        if((charLength(field,limit) && hasOnlyDigits(field))){
                            val = field.getText();
                            field.setBorder(borderDefault);
                        }
                        else{
                            val = "invalid";
                            field.setBorder(ERROR_BORDER);
                        }                        
                    }
                break;
                
                case LETTERS_LENGTH: 
                    if(limit == null || limit <= 0){
                        throw new IllegalArgumentException("Invalid value for limit");
                    }
                    else{
                        if((charLength(field,limit) && hasOnlyLetters(field))){
                            val = field.getText();
                            field.setBorder(borderDefault);
                        }
                        else{
                            val = "invalid";
                            field.setBorder(ERROR_BORDER);
                        }                    
                    }
                break;
                
                case REGEX: 
                    if(regex == null || regex.trim().isEmpty()){
                        throw new IllegalArgumentException("No regex pattern for matching");
                    }
                    else{
                        if(matchesRegex(field, regex)){
                            val = field.getText();
                            field.setBorder(borderDefault);
                        }
                        else{
                            val = "invalid";
                            field.setBorder(ERROR_BORDER);
                        }       
                    }
                break;

                case NO_VALIDATION:
                    if (field.getText().trim().isEmpty() || field.getText().equals(initialText)){
                            val = "invalid";
                            field.setBorder(ERROR_BORDER);
                    } 
                    else{
                        val = field.getText();
                        field.setBorder(borderDefault);
                    }
                break; 

                default:
                    throw new IllegalArgumentException("Invalid type of validation passed");
            }
        }
       
        return val;
    }
    
    //for many complusory fields to make sure they have been filled
    public static boolean validateCompulsoryTextFields(
        JTextComponent [] field, String [] initialText,int[] type, Integer[] limit, String[] regex, Border borderDefault
    ){
        boolean state = true;
        
        if(field.length == initialText.length){
            //for loop code for validation
            for(int i = 0; i < field.length;i++){
                if(validateSingleCompulsoryTextField(field[i], initialText[i],type[i], limit[i],regex[i], borderDefault) == false){
                    state = false;
                }
            }
        }
        else{
            //throw exception
            throw new IllegalArgumentException("Field length and initialText length do not match");
        }
        
        return state;
    }
    
    //for many complusory fields to make sure they have been filled
    public static boolean validateCompulsoryTextFieldsRegex(
        JTextComponent [] field, String [] initialText, String[] regex, Border borderDefault
    ){
        boolean state = true;
        
        if(field.length == initialText.length){
            //for loop code for validation
            for(int i = 0; i < field.length;i++){
                //validate data
                if (!field[i].getText().trim().isEmpty() && !field[i].getText().equals(initialText[i])) {
                    
                    if (matchesRegex(field[i], regex[i]) == false) {
                        state = false;
                        field[i].setBorder(ERROR_BORDER);
                    } else {
                        field[i].setBorder(borderDefault);
                    }
                }
            }
        }
        else{
            //throw exception
            throw new IllegalArgumentException("Field length and initialText length do not match");
        }
        
        return state;
    }
    
    //for many optional texfields to determne if they were filled or not
    
    public static String[] validateOptionalTextFields(
        JTextComponent [] field, String [] initialText,int[] type, Integer[] limit, String[] regex, Border borderDefault
    ){
        String[] values = new String[field.length]; //an array to store the values of the optional text field
        
        if(field.length == initialText.length){
            //for loop code for validation
            for(int i = 0; i < field.length;i++){                
                values[i] = validateSingleOptionalTextField(field[i], initialText[i], type[i], limit[i], regex[i], borderDefault);
            }
        }
        else{
            //throw exception
            throw new IllegalArgumentException("Field length and initialText length do not match");
        }
        
        return values;
    }
    
    //FOR COMBO BOXES
    public static boolean validateSingleCombo(JComboBox cmb, String initial,Border defaultBorder){
        boolean state = true;
        
        //date
        if (cmb.getSelectedItem().toString().trim().equals(initial)) {
            state = false;
            cmb.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 1));
        } else {
            cmb.setBorder(defaultBorder);
        }
        
        return state;
    }
    
    public static String validateSingleComboString(JComboBox cmb, String initial,Border defaultBorder){
        String val;
        
        //date
        if (cmb.getSelectedItem().toString().trim().equals(initial)) {
            val = "invalid";
            cmb.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 1));
        } else {
            cmb.setBorder(defaultBorder);
            val = cmb.getSelectedItem().toString().trim();
        }
        
        return val;
    }
    
    public static boolean validateCombos(JComboBox[] cmb, String[] initial,Border borderDefault){
        boolean state = true;
    
        if(cmb.length == initial.length){
            //for loop code for validation
            for(int i = 0; i < cmb.length;i++){
                state = validateSingleCombo(cmb[i],initial[i],borderDefault); //validate data
            }
        }
        else{
            //throw exception
            throw new IllegalArgumentException("Text area length and initialText length do not match");
        }
        
        return state;
    }
    
    public static String[] validateCombosString(JComboBox[] cmb, String[] initial,Border borderDefault){
        String val[] = new String[cmb.length];
    
        if(cmb.length == initial.length){
            //for loop code for validation
            for(int i = 0; i < cmb.length;i++){
                val[i] = validateSingleComboString(cmb[i],initial[i],borderDefault); //validate data
            }
        }
        else{
            //throw exception
            throw new IllegalArgumentException("Text area length and initialText length do not match");
        }
        
        return val;
    }
    
    //for custom creation of validation
    public abstract boolean customTextFieldValidation();
    public abstract boolean customTextFieldRegexValidation();
    public abstract boolean customComboValidation();
}

