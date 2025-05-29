const validatePassword = (password: string): { isValid: boolean; message: string | null } => {
  const specialCharRegex = /[!@#$%^&*(),.?":{}|<>]/;
  const upperCaseRegex = /[A-Z]/;
  const lowerCaseRegex = /[a-z]/;
  const numberRegex = /[0-9]/;

  if (!specialCharRegex.test(password)) {
    return { isValid: false, message: "Password must include at least one special character." };
  }
  
  if (!upperCaseRegex.test(password)) {
    return { isValid: false, message: "Password must include at least one uppercase letter." };
  }
  
  if (!lowerCaseRegex.test(password)) {
    return { isValid: false, message: "Password must include at least one lowercase letter." };
  }
  
  if (!numberRegex.test(password)) {
    return { isValid: false, message: "Password must include at least one number." };
  }

  return { isValid: true, message: null };
};

export default validatePassword;