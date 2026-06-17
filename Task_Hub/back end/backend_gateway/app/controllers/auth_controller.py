from fastapi import APIRouter
from app.services.proxy_service import *

router = APIRouter(
    prefix="/auth",
    tags=["Authentication"]
)

@router.post("/signup")
async def signup(data: dict):

    return await forward_post(
        "/users/register",
        data
    )


@router.post("/signin")
async def signin(data: dict):

    return await forward_post(
        "/users/login",
        data
    )