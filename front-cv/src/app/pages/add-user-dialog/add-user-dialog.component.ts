import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UserService } from '../../services/user-service';

@Component({
  selector: 'app-add-user-dialog',
  templateUrl: './add-user-dialog.component.html',
  styleUrls: ['./add-user-dialog.component.css'],
})
export class AddUserDialogComponent implements OnInit {
  userForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<AddUserDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { role: 'STUDENT' | 'HR' },
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.userForm = this.fb.group({
      prenom: ['', Validators.required],
      nom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });
  }

  onCancel(): void {
    this.dialogRef.close();
  }

  onSave(): void {
    if (this.userForm.valid) {
      const userData = {
        ...this.userForm.value,
        role: this.data.role,
      };

      this.userService.addUser(userData).subscribe(
        (response) => {
          this.dialogRef.close(response);
        },
        (error) => {
          console.error('Error adding user:', error);
        }
      );
    }
  }
}