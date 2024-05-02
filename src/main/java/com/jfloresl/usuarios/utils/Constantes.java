package com.jfloresl.usuarios.utils;

public class Constantes {
	public static String emailExistente= "Email ya fue registrado";
	public static String emailFormat = "^[A-Za-z0-9+_.-]+@dominio.cl$";
	public static String emailInvalid ="Email invalido";
	
	public static String phoneNotFound ="Telefono no encontrado";
	public static String phoneInvalid="Telefono invalido";
	public static String phoneDeleted="Telefono eliminado";
	
	public static String passwordFormat = "^(?=.*?\\d.*?\\d).*(?=.*[A-Z]).*(?=.*[a-z].*[a-z]).*$";
	public static String passwordInvalid = "Password invalido";

	public static String userNotFound = "Usuario no encontrado";
	public static String userDeleted = "Usuario eliminado";
	public static String userEmpty="No hay usuarios para mostrar";

	public static String serviceNotFound = "Servicio no encontrado";

	public static String idInvalid = "Id invalido";
	public static String tokenInvalid ="Token invalido";
	
	public static String jsonError="Error en request json";
	public static String missingError="Error Falta datos en request";
	public static String userInvalid="Nombre invalido";

	public static String userSaveError="Error al guardar usuario";

}
