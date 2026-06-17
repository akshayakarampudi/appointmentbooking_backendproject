from pydantic import BaseModel, EmailStr


class SignupSchema(BaseModel):
    name: str
    mobile: str
    email: EmailStr
    password: str


class SigninSchema(BaseModel):
    email: EmailStr
    password: str