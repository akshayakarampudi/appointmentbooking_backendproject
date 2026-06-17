from fastapi import APIRouter, Depends
from app.services.proxy_service import *
from app.middleware.auth_middleware import get_headers

router = APIRouter(
    prefix="/users",
    tags=["Users"]
)

@router.get("/all")
async def getUsers(
    headers=Depends(get_headers)
):

    return await forward_get(
        "/users/all",
        headers
    )


@router.get("/profile")
async def getProfile(
    headers=Depends(get_headers)
):

    return await forward_get(
        "/users/profile",
        headers
    )