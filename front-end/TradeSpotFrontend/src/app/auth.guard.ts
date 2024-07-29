
import {  CanActivateFn, RouterStateSnapshot, UrlTree } from '@angular/router';



export const authGuard: CanActivateFn = (route, state) => {
  let result = localStorage.getItem("user");
  if (result != null) return true;
  else return false;
};

  

