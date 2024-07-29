import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Category } from 'src/app/models/category';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-new-category',
  templateUrl: './new-category.component.html',
  styleUrls: ['./new-category.component.css'],
})
export class NewCategoryComponent {
  selectedFile: File;
  constructor(
    private categoryService: CategoryService,
    private router: Router
  ) {}

  onSubmit(data): void {
    this.categoryService
      .createCategory(data, this.selectedFile)
      .subscribe((result) => {
        if (result) {
          alert('Category Added Successfully');
        }
        setTimeout(() => {
          this.router.navigateByUrl('/adminCategory');
        }, 2000);
      });
    console.log(data);
  }

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
  }
}
